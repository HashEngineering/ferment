use std::collections::HashMap;
use proc_macro2::Ident;
use quote::format_ident;
use syn::{Attribute, ConstParam, Field, FnArg, GenericParam, Generics, ImplItem, ImplItemConst, ImplItemMethod, ImplItemType, Item, ItemFn, ItemMod, ItemTrait, Meta, NestedMeta, parse_quote, Path, PatType, PredicateType, ReturnType, Signature, TraitItem, TraitItemConst, TraitItemMethod, TraitItemType, TypeParam, TypeParamBound, Variant, WhereClause, WherePredicate};
use syn::punctuated::Punctuated;
use syn::token::Add;
use crate::composition::{TraitDecompositionPart1, TypeComposition};
use crate::context::{Scope, ScopeChain};
use crate::conversion::{ObjectConversion, ScopeItemConversion, TypeConversion};
use crate::ext::Join;
use crate::helper::collect_bounds;
use crate::holder::PathHolder;
use crate::visitor::Visitor;

pub trait Visiting {
    fn join_scope(&self, scope: &ScopeChain, visitor: &mut Visitor) -> Option<ScopeChain>;
    fn add_to_scope(&self, scope: &ScopeChain, visitor: &mut Visitor);
}

impl Visiting for Item {
    fn join_scope(&self, scope: &ScopeChain, visitor: &mut Visitor) -> Option<ScopeChain> {
        match self {
            Item::Struct(..) |
            Item::Enum(..) |
            Item::Fn(..) |
            Item::Trait(..) |
            Item::Type(..) |
            Item::Impl(..) |
            Item::Mod(..) => {
                let scope = scope.joined(self);
                self.add_to_scope(&scope, visitor);
                Some(scope)
            },
            _ => None
        }
    }
    fn add_to_scope(&self, scope: &ScopeChain, visitor: &mut Visitor) {
        match self {
            Item::Const(_) => {}
            Item::Enum(item_enum) => {
                add_itself_conversion(visitor, scope.parent_scope().unwrap(), &item_enum.ident, ObjectConversion::new_item(TypeConversion::Object(TypeComposition::new(scope.to_type(), Some(item_enum.generics.clone()))), ScopeItemConversion::Item(Item::Enum(item_enum.clone()))));
                visitor.add_full_qualified_trait_type_from_macro(&item_enum.attrs, scope);
                visitor.add_generic_chain(scope, &item_enum.generics);
                item_enum.variants.iter().for_each(|Variant { fields, .. }|
                    fields.iter().for_each(|Field { ty, .. }|
                        visitor.add_full_qualified_type_match(scope, ty)));

            }
            Item::Fn(ItemFn { sig, .. }) => {
                let sig_ident = &sig.ident;
                add_itself_conversion(visitor, scope.parent_scope().unwrap(), sig_ident, ObjectConversion::new_item(TypeConversion::Fn(TypeComposition::new(scope.to_type(), Some(sig.generics.clone()))), ScopeItemConversion::Fn(sig.clone())));
                add_full_qualified_signature(visitor, sig, scope);
            }
            Item::Impl(item_impl) => {
                match &item_impl.trait_ {
                    Some((_, path, _)) => {
                        let ty = parse_quote!(#path);
                        visitor.add_full_qualified_type_match(scope, &ty);
                    },
                    None => {}
                }
                visitor.add_generic_chain(scope, &item_impl.generics);
                item_impl.items.iter().for_each(|impl_item| {
                    match impl_item {
                        ImplItem::Const(ImplItemConst { ident, ty, expr: _, .. }) => {
                            add_local_type(visitor, ident, scope);
                            visitor.add_full_qualified_type_match(scope, ty);

                        },
                        ImplItem::Method(ImplItemMethod { sig, .. }) => {
                            add_local_type(visitor, &sig.ident, scope);
                            add_full_qualified_signature(visitor, sig, scope);
                        },
                        ImplItem::Type(ImplItemType { ident, ty, generics, .. }) => {
                            add_local_type(visitor, ident, scope);
                            visitor.add_full_qualified_type_match(scope, ty);
                            visitor.add_generic_chain(scope, generics);
                        },
                        _ => {}
                    }
                });
            }
            Item::Mod(item_mod) => {
                add_inner_module_conversion(visitor, item_mod, scope);
            }
            Item::Struct(item_struct) => {
                add_itself_conversion(visitor, scope.parent_scope().unwrap(), &item_struct.ident, ObjectConversion::new_item(TypeConversion::Object(TypeComposition::new(scope.to_type(), Some(item_struct.generics.clone()))), ScopeItemConversion::Item(Item::Struct(item_struct.clone()))));
                visitor.add_full_qualified_trait_type_from_macro(&item_struct.attrs, scope);
                visitor.add_generic_chain(scope, &item_struct.generics);
                item_struct.fields.iter().for_each(|Field { ty, .. }|
                    visitor.add_full_qualified_type_match(scope, ty));
            }
            Item::Trait(item_trait) => add_full_qualified_trait(visitor, item_trait, scope),
            Item::Type(item_type) => {
                add_itself_conversion(visitor, scope.parent_scope().unwrap(), &item_type.ident, ObjectConversion::new_item(TypeConversion::Object(TypeComposition::new(scope.to_type(), Some(item_type.generics.clone()))), ScopeItemConversion::Item(Item::Type(item_type.clone()))));
                visitor.add_generic_chain(scope, &item_type.generics);
                visitor.add_full_qualified_type_match(scope, &item_type.ty);
            }
            _ => {}
        }
    }
}
fn add_full_qualified_trait(visitor: &mut Visitor, item_trait: &ItemTrait, scope: &ScopeChain) {
    // println!("add_full_qualified_trait: {}: {}", item_trait.ident, scope);
    let ident = &item_trait.ident;
    let type_compo = TypeComposition::new(scope.to_type(), Some(item_trait.generics.clone()));
    let itself = ObjectConversion::new_item(
        TypeConversion::Trait(
            type_compo,
            TraitDecompositionPart1::from_trait_items(ident, &item_trait.items),
            add_bounds(visitor, &item_trait.supertraits, scope)),
        ScopeItemConversion::Item(Item::Trait(item_trait.clone())));

    // 1. Add itself to the scope as <Self, Item(Trait(..))>
    // 2. Add itself to the parent scope as <Ident, Item(Trait(..))>
    // println!("::: 1. ADD Self (local scope): <{}, {}> in [{}]", quote!(Self), itself, scope);
    // println!("::: 2. ADD Self: (parent scope) <{}, {}> in [{}]", quote!(#ident), itself, scope.parent_scope().unwrap());
    visitor.add_full_qualified_trait_match(&scope, item_trait, &itself);
    item_trait.items.iter().for_each(|trait_item|
        match trait_item {
            TraitItem::Method(TraitItemMethod { sig, .. }) => {
                let sig_ident = &sig.ident;
                let self_scope = scope.self_scope();
                let fn_self_scope = self_scope.self_scope.joined(sig_ident);
                add_local_type(visitor, sig_ident, scope);
                let object = ObjectConversion::new_item(TypeConversion::Fn(TypeComposition::new(parse_quote!(#fn_self_scope), Some(sig.generics.clone()))), ScopeItemConversion::Fn(sig.clone()));
                let fn_scope = ScopeChain::Fn {
                    self_scope: Scope::new(fn_self_scope, object),
                    parent_scope_chain: Box::new(scope.clone())
                };
                add_full_qualified_signature(visitor, sig, &fn_scope);
                visitor.add_generic_chain(&fn_scope, &sig.generics);
            },
            TraitItem::Type(TraitItemType { ident: type_ident, bounds, generics, .. }) => {
                add_local_type(visitor, type_ident, scope);
                add_bounds(visitor, bounds, scope);
                visitor.add_generic_chain(scope, generics);
            },
            TraitItem::Const(TraitItemConst { ident, ty, .. }) => {
                add_local_type(visitor, ident, scope);
                visitor.add_full_qualified_type_match(scope, ty);
            },
            _ => {}
        });
    visitor.scope_add_one(parse_quote!(#ident), itself.clone(), scope.parent_scope().unwrap());
    visitor.scope_add_one(parse_quote!(Self), itself, scope);
    visitor.add_generic_chain(&scope, &item_trait.generics);
}

fn add_full_qualified_signature(visitor: &mut Visitor, sig: &Signature, scope: &ScopeChain) {
    let Signature { output, inputs, generics, .. } = sig;
    if let ReturnType::Type(_arrow_token, ty) = output {
        visitor.add_full_qualified_type_match(scope, ty)
    }
    inputs.iter().for_each(|arg| if let FnArg::Typed(PatType { ty, .. }) = arg {
        visitor.add_full_qualified_type_match(scope, ty);
    });
    visitor.add_generic_chain(scope, generics);


    // let ty: Type = parse_quote!(#ident);
    // self.add_full_qualified_type_match(scope, &ty);
    // match scope.obj_root_chain() {
    //     Some(parent) => {
    //         let ty: TypeHolder = parse_quote!(#ident);
    //         // TODO: wrong here can be non-determined context
    //         let object = self.update_nested_generics(parent, &ty.0);
    //         self.scope_add_one(ty, object, parent);
    //
    //     },
    //     _ => {}
    // }
}

fn add_inner_module_conversion(visitor: &mut Visitor, item_mod: &ItemMod, scope: &ScopeChain) {
    println!("add_inner_module_conversion: {} in [{}]", item_mod.ident, scope);
    match &item_mod.content {
        None => {},
        Some((_, items)) => {
            items.into_iter().for_each(|item| match item {
                Item::Use(node) =>
                    visitor.fold_import_tree(scope, &node.tree, vec![]),
                Item::Trait(..) =>
                    item.add_to_scope(&scope.joined(item), visitor),
                Item::Fn(..) =>
                    item.add_to_scope(&scope.joined(item), visitor),
                Item::Struct(..) =>
                    item.add_to_scope(&scope.joined(item), visitor),
                Item::Enum(..) =>
                    item.add_to_scope(&scope.joined(item), visitor),
                Item::Type(..) =>
                    item.add_to_scope(&scope.joined(item), visitor),
                Item::Impl(..) =>
                    item.add_to_scope(&scope.joined(item), visitor),
                Item::Mod(..) =>
                    item.add_to_scope(&scope.joined(item), visitor),
                _ => {}
            })
        }
    }
}

fn add_local_type(visitor: &mut Visitor, ident: &Ident, scope: &ScopeChain) {
    let local_type = parse_quote!(Self::#ident);
    visitor.add_full_qualified_type_match(scope, &local_type);
}

fn add_bounds(visitor: &mut Visitor, bounds: &Punctuated<TypeParamBound, Add>, scope: &ScopeChain) -> Vec<Path> {
    let bounds = collect_bounds(bounds);
    bounds.iter().for_each(|path| {
        let ty = parse_quote!(#path);
        visitor.add_full_qualified_type_match(scope, &ty);
    });
    bounds
}

pub fn create_generics_chain(visitor: &mut Visitor, generics: &Generics, scope: &ScopeChain) -> HashMap<PathHolder, Vec<Path>> {
    let mut generics_chain: HashMap<PathHolder, Vec<Path>> = HashMap::new();
    generics.params.iter().for_each(|generic_param| {
        match generic_param { // T: Debug + Clone
            GenericParam::Type(TypeParam { ident: generic_ident, bounds, .. }) => {
                generics_chain.insert(parse_quote!(#generic_ident), add_bounds(visitor, bounds, scope));
            },
            GenericParam::Const(ConstParam { ty, .. }) => {
                visitor.add_full_qualified_type_match(scope, ty);
            },
            GenericParam::Lifetime(_lifetime) => {},
        }
    });
    match &generics.where_clause {
        Some(WhereClause { predicates, .. }) => {
            predicates.iter().for_each(|predicate| match predicate {
                WherePredicate::Type(PredicateType { bounds, bounded_ty, .. }) => {
                    // where T: Debug + Clone, T::Item: XX,
                    generics_chain.insert(parse_quote!(#bounded_ty), add_bounds(visitor, bounds, scope));
                    visitor.add_full_qualified_type_match(scope, bounded_ty);
                },
                WherePredicate::Lifetime(_) => {}
                WherePredicate::Eq(_) => {}
            })
        },
        None => {}
    }
    generics_chain
}

fn add_itself_conversion(visitor: &mut Visitor, scope: &ScopeChain, ident: &Ident, object: ObjectConversion) {
    visitor.scope_add_one(parse_quote!(#ident), object, scope);
}

pub fn extract_trait_names(attrs: &[Attribute]) -> Vec<Path> {
    let mut paths = Vec::<Path>::new();
    attrs.iter().for_each(|attr| {
        if attr.path.segments
            .iter()
            .any(|segment| segment.ident == format_ident!("export")) {
            if let Ok(Meta::List(meta_list)) = attr.parse_meta() {
                meta_list.nested.iter().for_each(|meta| {
                    if let NestedMeta::Meta(Meta::Path(path)) = meta {
                        paths.push(path.clone());
                    }
                });
            }
        }
    });
    paths
}

pub fn add_trait_names(visitor: &mut Visitor, scope: &ScopeChain, item_trait_paths: &Vec<Path>) {
    item_trait_paths.iter().for_each(|trait_name|
        visitor.add_full_qualified_type_match(scope, &parse_quote!(#trait_name)));

}