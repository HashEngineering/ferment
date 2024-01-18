use std::collections::HashMap;
use std::fmt::Formatter;
use syn::{GenericArgument, Ident, ItemTrait, parse_quote, Path, PathArguments, Type, TypePath, TypeReference};
use crate::Config;
use crate::conversion::TypeConversion;
use crate::formatter::{format_global_context, format_token_stream};
use crate::holder::{PathHolder, TypeHolder};

#[derive(Clone)]
pub struct TraitCompositionPart1 {
    pub item: ItemTrait,
    pub implementors: Vec<TypeConversion>,
}

impl std::fmt::Debug for TraitCompositionPart1 {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        let s = self.implementors.iter().map(|i| format_token_stream(i)).collect::<Vec<_>>().join("\n\n");
        f.write_str(format!("{}:\n  {}", format_token_stream(&self.item.ident), s).as_str())
    }
}

impl TraitCompositionPart1 {
    pub fn new(item: ItemTrait) -> Self {
        Self { item, implementors: vec![] }
    }
}

#[derive(Clone, Default)]
pub struct GlobalContext {
    pub config: Config,
    pub scope_types: HashMap<PathHolder, HashMap<TypeHolder, TypeConversion>>,
    // crate::asyn::query::Query: [T: [TransportRequest]]
    pub used_generics_at_scopes: HashMap<PathHolder, HashMap<PathHolder, Vec<Path>>>,
    pub traits_dictionary: HashMap<PathHolder, HashMap<Ident, TraitCompositionPart1>>,
    pub used_traits_dictionary: HashMap<PathHolder, Vec<PathHolder>>,
    pub custom_conversions: HashMap<PathHolder, HashMap<TypeHolder, TypeConversion>>,
    pub used_imports_at_scopes: HashMap<PathHolder, HashMap<PathHolder, Path>>,
}

impl std::fmt::Debug for GlobalContext {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        f.write_str(&format_global_context(self))
    }
}

impl std::fmt::Display for GlobalContext {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        std::fmt::Debug::fmt(self, f)
    }
}

impl GlobalContext {
    pub fn with_config(config: Config) -> Self {
        Self { config, ..Default::default() }
    }
    pub fn fermented_mod_name(&self) -> &str {
        &self.config.mod_name
    }

    pub fn scope_generics_mut(&mut self, scope: &PathHolder) -> &mut HashMap<PathHolder, Vec<Path>> {
        self.used_generics_at_scopes
            .entry(scope.clone())
            .or_default()
    }
    pub fn scope_types_mut(&mut self, scope: &PathHolder) -> &mut HashMap<TypeHolder, TypeConversion> {
        self.scope_types
            .entry(scope.clone())
            .or_default()
    }
    pub fn maybe_scope_type(&self, ty: &Type, scope: &PathHolder) -> Option<&TypeConversion> {
        let tc = match ty {
            Type::Reference(TypeReference { elem, .. }) => TypeHolder::from(elem),
            _ => TypeHolder::from(ty)
        };
        // println!("GLOBAL:\n{}", self);
        self.scope_types
            .get(scope)
            .and_then(|dict| dict.get(&tc))
    }

    pub fn resolve_trait_type(&self, from_type: &Type) -> Option<&TypeConversion> {
        // RESOLVE PATHS
        // Self::asyn::query::TransportRequest::Client::Error
        // ? [Self::asyn::query::TransportRequest::Client::Error] Self
        // : [Self::asyn::query::TransportRequest::Client] Self::Error
        // : [Self::asyn::query::TransportRequest] Self::Client::Error
        //  : [Self::asyn::query::TransportRequest] Self::Client -> [Self::asyn::query::TransportClient] Self::Error

        // aa::bb::cc::dd::ee
        // 1. a) [aa::bb::cc::dd::ee] Self
        // 2. a) [aa::bb::cc::dd] Self::ee
        // 3. a) [aa::bb::cc::dd] Self, [Self::ee]
        // 4. a) [aa::bb::cc] Self::dd::ee, b) [aa::bb::cc] Self::dd
        let current_scope: PathHolder = parse_quote!(#from_type);
        let mut i = 0;
        let mut maybe_trait: Option<&TypeConversion>  = None;
        while i < current_scope.len() && maybe_trait.is_none() {
            let (root, head) = current_scope.split_and_join_self(i);
            let ty = parse_quote!(#head);
            maybe_trait = self.maybe_scope_type(&ty, &root);
            if i > 0 {
                if let Some(TypeConversion::Trait(trait_ty, decomposition)) = maybe_trait {
                    let ident = &head.0.segments.last().unwrap().ident;
                    println!("FFI (has decomposition) for: {}: {}", format_token_stream(ident), format_token_stream(trait_ty));
                    if let Some(trait_type) = decomposition.types.get(ident) {
                        let tt = trait_type.trait_bounds.first().unwrap();
                        println!("FFI (first bound) {}", format_token_stream(&tt.path));
                        let tt_type = parse_quote!(#tt);
                        maybe_trait = self.maybe_scope_type(&tt_type, &root);
                        println!("FFI (first bound full) {:?}", maybe_trait);
                    }
                }
            }
            println!("FFI (resolve....) for: {} in [{}] ===> {:?}", format_token_stream(&head), format_token_stream(&root), maybe_trait);
            i += 1;
        }
        maybe_trait
    }

    pub fn maybe_scope_type_or_parent_type(&self, ty: &Type, scope: &PathHolder) -> Option<TypeConversion> {
        self.maybe_scope_type(ty, scope)
            .map_or({
                        let scope = scope.popped();
                        self.maybe_scope_type(ty, &scope)
                            .map(|ty| ty.clone())
                    }, |ty| Some(ty.clone()))
    }

    // Expect here smth like "crate::path::Struct" or "std::error::Error"
    pub fn maybe_scope_import_path(&self, scope: &PathHolder, ident: &PathHolder) -> Option<&Path> {
        let x = self.used_imports_at_scopes
            .get(scope)
            .and_then(|scope_imports|
                scope_imports.get(ident));
        // println!("maybe_scope_import_path: {}: [{}] --> {}", format_token_stream(scope), format_token_stream(ident), format_token_stream(&x));
        x
    }

    pub fn maybe_generic_bounds(&self, scope: &PathHolder, ident: &PathHolder) -> Option<&Vec<Path>> {
        let x = self.used_generics_at_scopes.get(scope)
            .and_then(|scope_generics| scope_generics.get(ident));
        x
    }

    pub fn maybe_scope_generic_bounds_or_parent(&self, scope: &PathHolder, ident: &PathHolder) -> Option<&Path> {
        self.maybe_generic_bounds(scope, ident).and_then(|generic_bounds| {
            let first_bound = generic_bounds.first().unwrap();
            let first_bound_as_scope = PathHolder::from(first_bound);
            self.maybe_scope_import_path(scope, &first_bound_as_scope)
                .or(self.maybe_scope_import_path(&scope.popped(), &first_bound_as_scope))
        })
    }

    pub fn maybe_scope_import_path_or_parent(&self, scope: &PathHolder, parent_scope: &PathHolder, ident: &PathHolder) -> Option<&Path> {
        self.maybe_scope_import_path(scope, ident)
            .or(self.maybe_scope_import_path(parent_scope, ident))
    }

    pub fn maybe_custom_conversion(&self, ty: &Type) -> Option<Type> {
        //println!("maybe_custom_conversion: {}", format_token_stream(ty));
        self.custom_conversions.keys()
                .find_map(|scope| self.replace_custom_conversion(scope, ty))
    }

    pub fn maybe_trait(&self, full_ty: &Type) -> Option<TraitCompositionPart1> {
        let full_scope: PathHolder = parse_quote!(#full_ty);
        let last_ident = full_scope.head();
        let scope = full_scope.popped();
        // let scope = Scope::extract_type_scope(full_ty);
        // let scope: Scope = parse_quote!(#full_ty);
        // let last_ident = &scope.path.segments.last().unwrap().ident;
        println!("maybe_trait: [{}]: {}", scope, last_ident);
        self.traits_dictionary.get(&scope)
            .and_then(|scope_traits| scope_traits.get(&last_ident))
            .cloned()
    }

    fn replacement_for<'a>(&'a self, ty: &'a Type, scope: &'a PathHolder) -> Option<&'a TypeConversion> {
        let tc = TypeHolder::from(ty);
        self.custom_conversions
            .get(scope)
            .and_then(|conversion_pairs| conversion_pairs.get(&tc))
    }

    fn replace_custom_conversion(&self, scope: &PathHolder, ty: &Type) -> Option<Type> {
        let mut custom_type = ty.clone();
        let mut replaced = false;
        if let Type::Path(TypePath { path: Path { segments, .. }, .. }) = &mut custom_type {
            for segment in &mut *segments {
                if let PathArguments::AngleBracketed(angle_bracketed_generic_arguments) = &mut segment.arguments {
                    for arg in &mut angle_bracketed_generic_arguments.args {
                        if let GenericArgument::Type(inner_type) = arg {
                            if let Some(replaced_type) = self.replace_custom_conversion(scope, inner_type) {
                                *arg = GenericArgument::Type(replaced_type);
                                replaced = true;
                            }
                        }
                    }
                }
            }
            if let Some(type_type) = self.replacement_for(ty, scope) {
                if let Type::Path(TypePath { path: Path { segments: new_segments, .. }, .. }) = type_type.ty() {
                    *segments = new_segments.clone();
                    replaced = true;
                }
            }
        }
        // println!("replace_custom_conversion: {}: {}: {}",
        //          format_token_stream(scope),
        //          format_token_stream(ty),
        //          format_token_stream(&custom_type));
        replaced.then_some(custom_type)
    }

    // fn find_trait_item_full_paths_pair(&self, ) -> (Scope, Scope) {
    //     self.used_traits_dictionary.iter()
    //         .for_each(|(item_full_path, trait_path_chunks)| {
    //             trait_path_chunks.iter()
    //                 .for_each(|trait_ident_or_chunk| {
    //                     let trait_ident = trait_ident_or_chunk.root_ident();
    //                     // Restore full trait path using imports
    //                     // TODO: can be chunk so need to handle not only idents
    //                     let trait_scope = if let Some(import) = self.maybe_scope_import_path(item_full_path, &trait_ident) {
    //                         Scope::from(import)
    //                     } else {
    //                         item_full_path.popped().joined(&trait_ident)
    //                     };
    //                     (trait_scope, item_full_path)
    //                 });
    //
    //         });
    //
    // }

    // pub fn inject_types_from_traits_implementation(&mut self) {
    //     let self_tc = TypeHolder::new(parse_quote!(Self));
    //     self.used_traits_dictionary.iter()
    //         .for_each(|(item_full_path, trait_path_chunks)| {
    //             trait_path_chunks.iter()
    //                 .for_each(|trait_ident_or_chunk| {
    //                     let trait_ident = trait_ident_or_chunk.root_ident();
    //                     // Restore full trait path using imports
    //                     // TODO: can be chunk so need to handle not only idents
    //                     let trait_scope = if let Some(import) = self.maybe_scope_import_path(item_full_path, &trait_ident) {
    //                         Scope::from(import)
    //                     } else {
    //                         item_full_path.popped().joined(&trait_ident)
    //                     };
    //                     println!("inject_types_from_traits_implementation: [{}]: {}: {}", format_token_stream(item_full_path), format_token_stream(trait_ident_or_chunk), format_token_stream(&trait_scope));
    //                     if let Some(types_used_by_trait) = self.scope_types.get(&trait_scope).cloned() {
    //                         // Copy them to implementor's types
    //                         println!("copy types except self:\n   {}", format_types_dict(&types_used_by_trait));
    //                         // TODO: do we need to replace Self to <Self as #trait>?
    //                         let types = types_used_by_trait.into_iter().filter(|(tc, tyty)| {
    //
    //                             !self_tc.eq(tc)
    //                         });
    //                         self.scope_types.entry(item_full_path.clone())
    //                             .or_default()
    //                             .extend(types);
    //                     }
    //             });
    //
    //     });
    // }
    pub fn inject_types_from_traits_implementation(&mut self) {
        let self_tc = TypeHolder::new(parse_quote!(Self));

        // Collect necessary data in a Vec to avoid borrowing `self` while iterating.
        let mut trait_data = Vec::new();

        for (item_full_path, trait_path_chunks) in &self.used_traits_dictionary {
            for trait_ident_or_chunk in trait_path_chunks {
                // let trait_ident = trait_ident_or_chunk.root_ident();
                let trait_scope = if let Some(import) = self.maybe_scope_import_path(item_full_path, trait_ident_or_chunk) {
                    PathHolder::from(import)
                } else {
                    item_full_path.popped().joined_path(trait_ident_or_chunk.0.clone())
                };
                println!("inject_types_from_traits_implementation: [{}]: {}: {}", format_token_stream(item_full_path), format_token_stream(trait_ident_or_chunk), format_token_stream(&trait_scope));

                if let Some(types_used_by_trait) = self.scope_types.get(&trait_scope) {
                    trait_data.push((item_full_path.clone(), types_used_by_trait.clone()));
                }
            }
        }

        // Now, iterate over the collected data and modify `self.scope_types`.
        for (item_full_path, types_used_by_trait) in trait_data {
            // println!("copy types except self:\n   {}", format_types_dict(&types_used_by_trait));
            let types = types_used_by_trait.into_iter().filter(|(tc, _)| !self_tc.eq(tc));
            self.scope_types
                .entry(item_full_path)
                .or_default()
                .extend(types);
        }
    }}