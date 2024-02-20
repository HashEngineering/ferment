# [allow (clippy :: let_and_return , clippy :: suspicious_else_formatting , clippy :: redundant_field_names , dead_code , non_camel_case_types , non_snake_case , non_upper_case_globals , redundant_semicolons , unused_braces , unused_imports , unused_unsafe , unused_variables , unused_qualifications)] pub mod types { # [doc = "FFI-representation of the [`crate::SomeStruct`]"] # [repr (C)] # [derive (Clone)] pub struct SomeStruct { pub name : * mut std :: os :: raw :: c_char , } impl ferment_interfaces :: FFIConversion < crate :: SomeStruct > for SomeStruct { unsafe fn ffi_from_const (ffi : * const SomeStruct) -> crate :: SomeStruct { let ffi_ref = & * ffi ; crate :: SomeStruct { name : ferment_interfaces :: FFIConversion :: ffi_from (ffi_ref . name) , } } unsafe fn ffi_to_const (obj : crate :: SomeStruct) -> * const SomeStruct { ferment_interfaces :: boxed (SomeStruct { name : ferment_interfaces :: FFIConversion :: ffi_to (obj . name) , }) } unsafe fn destroy (ffi : * mut SomeStruct) { ferment_interfaces :: unbox_any (ffi) ; } } impl Drop for SomeStruct { fn drop (& mut self) { unsafe { let ffi_ref = self ; < std :: os :: raw :: c_char as ferment_interfaces :: FFIConversion < String >> :: destroy (ffi_ref . name) ; } } } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn SomeStruct_ctor (name : * mut std :: os :: raw :: c_char) -> * mut SomeStruct { ferment_interfaces :: boxed (SomeStruct { name , }) } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn SomeStruct_destroy (ffi : * mut SomeStruct) { ferment_interfaces :: unbox_any (ffi) ; } pub mod model { pub mod snapshot { # [doc = "FFI-representation of the [`crate::model::snapshot::LLMQSnapshot`]"] # [repr (C)] # [derive (Clone)] pub struct LLMQSnapshot { pub member_list : * mut crate :: fermented :: generics :: Vec_u8 , pub skip_list : * mut crate :: fermented :: generics :: Vec_i32 , pub skip_list_mode : * mut crate :: fermented :: types :: model :: snapshot :: LLMQSnapshotSkipMode , pub option_vec : * mut crate :: fermented :: generics :: Vec_u8 , } impl ferment_interfaces :: FFIConversion < crate :: model :: snapshot :: LLMQSnapshot > for LLMQSnapshot { unsafe fn ffi_from_const (ffi : * const LLMQSnapshot) -> crate :: model :: snapshot :: LLMQSnapshot { let ffi_ref = & * ffi ; crate :: model :: snapshot :: LLMQSnapshot { member_list : ferment_interfaces :: FFIConversion :: ffi_from (ffi_ref . member_list) , skip_list : ferment_interfaces :: FFIConversion :: ffi_from (ffi_ref . skip_list) , skip_list_mode : ferment_interfaces :: FFIConversion :: ffi_from (ffi_ref . skip_list_mode) , option_vec : ferment_interfaces :: FFIConversion :: ffi_from_opt (ffi_ref . option_vec) , } } unsafe fn ffi_to_const (obj : crate :: model :: snapshot :: LLMQSnapshot) -> * const LLMQSnapshot { ferment_interfaces :: boxed (LLMQSnapshot { member_list : ferment_interfaces :: FFIConversion :: ffi_to (obj . member_list) , skip_list : ferment_interfaces :: FFIConversion :: ffi_to (obj . skip_list) , skip_list_mode : ferment_interfaces :: FFIConversion :: ffi_to (obj . skip_list_mode) , option_vec : match obj . option_vec { Some (vec) => ferment_interfaces :: FFIConversion :: ffi_to (vec) , None => std :: ptr :: null_mut () , } , }) } unsafe fn destroy (ffi : * mut LLMQSnapshot) { ferment_interfaces :: unbox_any (ffi) ; } } impl Drop for LLMQSnapshot { fn drop (& mut self) { unsafe { let ffi_ref = self ; ferment_interfaces :: unbox_any (ffi_ref . member_list) ; ; ferment_interfaces :: unbox_any (ffi_ref . skip_list) ; ; ferment_interfaces :: unbox_any (ffi_ref . skip_list_mode) ; ; if ! ffi_ref . option_vec . is_null () { ferment_interfaces :: unbox_any (ffi_ref . option_vec) ; } ; } } } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn LLMQSnapshot_ctor (member_list : * mut crate :: fermented :: generics :: Vec_u8 , skip_list : * mut crate :: fermented :: generics :: Vec_i32 , skip_list_mode : * mut crate :: fermented :: types :: model :: snapshot :: LLMQSnapshotSkipMode , option_vec : * mut crate :: fermented :: generics :: Vec_u8) -> * mut LLMQSnapshot { ferment_interfaces :: boxed (LLMQSnapshot { member_list , skip_list , skip_list_mode , option_vec , }) } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn LLMQSnapshot_destroy (ffi : * mut LLMQSnapshot) { ferment_interfaces :: unbox_any (ffi) ; } # [doc = "FFI-representation of the [`LLMQSnapshotSkipMode`]"] # [repr (C)] # [derive (Clone)] pub enum LLMQSnapshotSkipMode { NoSkipping = 0 , SkipFirst = 1 , SkipExcept = 2 , SkipAll = 3 , } impl ferment_interfaces :: FFIConversion < crate :: model :: snapshot :: LLMQSnapshotSkipMode > for LLMQSnapshotSkipMode { unsafe fn ffi_from_const (ffi : * const LLMQSnapshotSkipMode) -> crate :: model :: snapshot :: LLMQSnapshotSkipMode { let ffi_ref = & * ffi ; match ffi_ref { LLMQSnapshotSkipMode :: NoSkipping => crate :: model :: snapshot :: LLMQSnapshotSkipMode :: NoSkipping , LLMQSnapshotSkipMode :: SkipFirst => crate :: model :: snapshot :: LLMQSnapshotSkipMode :: SkipFirst , LLMQSnapshotSkipMode :: SkipExcept => crate :: model :: snapshot :: LLMQSnapshotSkipMode :: SkipExcept , LLMQSnapshotSkipMode :: SkipAll => crate :: model :: snapshot :: LLMQSnapshotSkipMode :: SkipAll , } } unsafe fn ffi_to_const (obj : crate :: model :: snapshot :: LLMQSnapshotSkipMode) -> * const LLMQSnapshotSkipMode { ferment_interfaces :: boxed (match obj { crate :: model :: snapshot :: LLMQSnapshotSkipMode :: NoSkipping => LLMQSnapshotSkipMode :: NoSkipping , crate :: model :: snapshot :: LLMQSnapshotSkipMode :: SkipFirst => LLMQSnapshotSkipMode :: SkipFirst , crate :: model :: snapshot :: LLMQSnapshotSkipMode :: SkipExcept => LLMQSnapshotSkipMode :: SkipExcept , crate :: model :: snapshot :: LLMQSnapshotSkipMode :: SkipAll => LLMQSnapshotSkipMode :: SkipAll , }) } unsafe fn destroy (ffi : * mut LLMQSnapshotSkipMode) { ferment_interfaces :: unbox_any (ffi) ; } } impl Drop for LLMQSnapshotSkipMode { fn drop (& mut self) { unsafe { match self { LLMQSnapshotSkipMode :: NoSkipping => { } , LLMQSnapshotSkipMode :: SkipFirst => { } , LLMQSnapshotSkipMode :: SkipExcept => { } , LLMQSnapshotSkipMode :: SkipAll => { } , } } } } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn LLMQSnapshotSkipMode_NoSkipping_ctor () -> * mut LLMQSnapshotSkipMode { ferment_interfaces :: boxed (LLMQSnapshotSkipMode :: NoSkipping) } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn LLMQSnapshotSkipMode_SkipFirst_ctor () -> * mut LLMQSnapshotSkipMode { ferment_interfaces :: boxed (LLMQSnapshotSkipMode :: SkipFirst) } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn LLMQSnapshotSkipMode_SkipExcept_ctor () -> * mut LLMQSnapshotSkipMode { ferment_interfaces :: boxed (LLMQSnapshotSkipMode :: SkipExcept) } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn LLMQSnapshotSkipMode_SkipAll_ctor () -> * mut LLMQSnapshotSkipMode { ferment_interfaces :: boxed (LLMQSnapshotSkipMode :: SkipAll) } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn LLMQSnapshotSkipMode_destroy (ffi : * mut LLMQSnapshotSkipMode) { ferment_interfaces :: unbox_any (ffi) ; } } } pub mod some_package { # [doc = "FFI-representation of the [`crate::some_package::SomeGenericStruct`]"] # [repr (C)] # [derive (Clone)] pub struct SomeGenericStruct { pub obj : * mut FP , } impl ferment_interfaces :: FFIConversion < crate :: some_package :: SomeGenericStruct > for SomeGenericStruct { unsafe fn ffi_from_const (ffi : * const SomeGenericStruct) -> crate :: some_package :: SomeGenericStruct { let ffi_ref = & * ffi ; crate :: some_package :: SomeGenericStruct { obj : ferment_interfaces :: FFIConversion :: ffi_from (ffi_ref . obj) , } } unsafe fn ffi_to_const (obj : crate :: some_package :: SomeGenericStruct) -> * const SomeGenericStruct { ferment_interfaces :: boxed (SomeGenericStruct { obj : ferment_interfaces :: FFIConversion :: ffi_to (obj . obj) , }) } unsafe fn destroy (ffi : * mut SomeGenericStruct) { ferment_interfaces :: unbox_any (ffi) ; } } impl Drop for SomeGenericStruct { fn drop (& mut self) { unsafe { let ffi_ref = self ; ferment_interfaces :: unbox_any (ffi_ref . obj) ; ; } } } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn SomeGenericStruct_ctor (obj : * mut FP) -> * mut SomeGenericStruct { ferment_interfaces :: boxed (SomeGenericStruct { obj , }) } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn SomeGenericStruct_destroy (ffi : * mut SomeGenericStruct) { ferment_interfaces :: unbox_any (ffi) ; } # [repr (C)] # [derive (Clone)] pub struct SomeTrait_VTable { } # [repr (C)] # [derive (Clone)] pub struct SomeTrait { pub object : * const () , pub vtable : * const SomeTrait_VTable } } } # [allow (clippy :: let_and_return , clippy :: suspicious_else_formatting , clippy :: redundant_field_names , dead_code , non_camel_case_types , non_snake_case , non_upper_case_globals , redundant_semicolons , unused_braces , unused_imports , unused_unsafe , unused_variables , unused_qualifications)] pub mod generics { # [repr (C)] # [derive (Clone)] pub struct Vec_u8 { pub count : usize , pub values : * mut u8 , } impl ferment_interfaces :: FFIConversion < Vec < u8 > > for Vec_u8 { unsafe fn ffi_from_const (ffi : * const Vec_u8) -> Vec < u8 > { ferment_interfaces :: FFIVecConversion :: decode (& * ffi) } unsafe fn ffi_to_const (obj : Vec < u8 >) -> * const Vec_u8 { ferment_interfaces :: FFIVecConversion :: encode (obj) } unsafe fn destroy (ffi : * mut Vec_u8) { ferment_interfaces :: unbox_any (ffi) ; ; } } impl ferment_interfaces :: FFIVecConversion for Vec_u8 { type Value = Vec < u8 > ; unsafe fn decode (& self) -> Self :: Value { ferment_interfaces :: from_primitive_vec (self . values , self . count) } unsafe fn encode (obj : Self :: Value) -> * mut Self { ferment_interfaces :: boxed (Self { count : obj . len () , values : ferment_interfaces :: boxed_vec (obj) }) } } impl Drop for Vec_u8 { fn drop (& mut self) { unsafe { ferment_interfaces :: unbox_vec_ptr (self . values , self . count) ; } } } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn Vec_u8_ctor (values : * mut u8 , count : usize) -> * mut Vec_u8 { ferment_interfaces :: boxed (Vec_u8 { count , values }) } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn Vec_u8_destroy (ffi : * mut Vec_u8) { ferment_interfaces :: unbox_any (ffi) ; } # [repr (C)] # [derive (Clone)] pub struct Vec_i32 { pub count : usize , pub values : * mut i32 , } impl ferment_interfaces :: FFIConversion < Vec < i32 > > for Vec_i32 { unsafe fn ffi_from_const (ffi : * const Vec_i32) -> Vec < i32 > { ferment_interfaces :: FFIVecConversion :: decode (& * ffi) } unsafe fn ffi_to_const (obj : Vec < i32 >) -> * const Vec_i32 { ferment_interfaces :: FFIVecConversion :: encode (obj) } unsafe fn destroy (ffi : * mut Vec_i32) { ferment_interfaces :: unbox_any (ffi) ; ; } } impl ferment_interfaces :: FFIVecConversion for Vec_i32 { type Value = Vec < i32 > ; unsafe fn decode (& self) -> Self :: Value { ferment_interfaces :: from_primitive_vec (self . values , self . count) } unsafe fn encode (obj : Self :: Value) -> * mut Self { ferment_interfaces :: boxed (Self { count : obj . len () , values : ferment_interfaces :: boxed_vec (obj) }) } } impl Drop for Vec_i32 { fn drop (& mut self) { unsafe { ferment_interfaces :: unbox_vec_ptr (self . values , self . count) ; } } } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn Vec_i32_ctor (values : * mut i32 , count : usize) -> * mut Vec_i32 { ferment_interfaces :: boxed (Vec_i32 { count , values }) } # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn Vec_i32_destroy (ffi : * mut Vec_i32) { ferment_interfaces :: unbox_any (ffi) ; } } # [allow (clippy :: let_and_return , clippy :: suspicious_else_formatting , clippy :: redundant_field_names , dead_code , non_camel_case_types , non_snake_case , non_upper_case_globals , redundant_semicolons , unused_braces , unused_imports , unused_unsafe , unused_variables , unused_qualifications)] pub mod vtables { }