# [allow (clippy :: all , dead_code , redundant_semicolons , unused_braces , unused_imports , unused_unsafe , unused_variables)] pub mod types { use crate :: SomeStruct ; pub mod model { pub mod snapshot { use crate :: model :: snapshot :: LLMQSnapshot ; use crate :: model :: snapshot :: LLMQSnapshotSkipMode ; use crate :: fermented :: generics :: Vec_i32_FFI ; use crate :: fermented :: generics :: Vec_u8_FFI ; # [doc = "FFI-representation of the LLMQSnapshotSkipMode"] # [repr (C)] # [allow (non_camel_case_types)] # [derive (Clone)] pub enum LLMQSnapshotSkipMode_FFI { NoSkipping = 0 , SkipFirst = 1 , SkipExcept = 2 , SkipAll = 3 , } impl ferment_interfaces :: FFIConversion < LLMQSnapshotSkipMode > for LLMQSnapshotSkipMode_FFI { unsafe fn ffi_from_const (ffi : * const LLMQSnapshotSkipMode_FFI) -> LLMQSnapshotSkipMode { let ffi_ref = & * ffi ; match ffi_ref { LLMQSnapshotSkipMode_FFI :: NoSkipping => LLMQSnapshotSkipMode :: NoSkipping , LLMQSnapshotSkipMode_FFI :: SkipFirst => LLMQSnapshotSkipMode :: SkipFirst , LLMQSnapshotSkipMode_FFI :: SkipExcept => LLMQSnapshotSkipMode :: SkipExcept , LLMQSnapshotSkipMode_FFI :: SkipAll => LLMQSnapshotSkipMode :: SkipAll , } } unsafe fn ffi_to_const (obj : LLMQSnapshotSkipMode) -> * const LLMQSnapshotSkipMode_FFI { ferment_interfaces :: boxed (match obj { LLMQSnapshotSkipMode :: NoSkipping => LLMQSnapshotSkipMode_FFI :: NoSkipping , LLMQSnapshotSkipMode :: SkipFirst => LLMQSnapshotSkipMode_FFI :: SkipFirst , LLMQSnapshotSkipMode :: SkipExcept => LLMQSnapshotSkipMode_FFI :: SkipExcept , LLMQSnapshotSkipMode :: SkipAll => LLMQSnapshotSkipMode_FFI :: SkipAll , }) } unsafe fn destroy (ffi : * mut LLMQSnapshotSkipMode_FFI) { ferment_interfaces :: unbox_any (ffi) ; } } impl Drop for LLMQSnapshotSkipMode_FFI { fn drop (& mut self) { unsafe { match self { LLMQSnapshotSkipMode_FFI :: NoSkipping => { } , LLMQSnapshotSkipMode_FFI :: SkipFirst => { } , LLMQSnapshotSkipMode_FFI :: SkipExcept => { } , LLMQSnapshotSkipMode_FFI :: SkipAll => { } , } } } } # [allow (non_snake_case)] # [no_mangle] pub unsafe extern "C" fn LLMQSnapshotSkipMode_FFI_destroy (ffi : * mut LLMQSnapshotSkipMode_FFI) { ferment_interfaces :: unbox_any (ffi) ; } # [doc = "FFI-representation of the # [doc = \"FFI-representation of the LLMQSnapshot\"]"] # [repr (C)] # [derive (Clone)] # [allow (non_camel_case_types)] pub struct LLMQSnapshot_FFI { pub member_list : * mut Vec_u8_FFI , pub skip_list : * mut Vec_i32_FFI , pub skip_list_mode : * mut LLMQSnapshotSkipMode_FFI , } impl ferment_interfaces :: FFIConversion < LLMQSnapshot > for LLMQSnapshot_FFI { unsafe fn ffi_from_const (ffi : * const LLMQSnapshot_FFI) -> LLMQSnapshot { let ffi_ref = & * ffi ; LLMQSnapshot { member_list : { let vec = & * ffi_ref . member_list ; ferment_interfaces :: from_simple_vec (vec . values , vec . count) } , skip_list : { let vec = & * ffi_ref . skip_list ; ferment_interfaces :: from_simple_vec (vec . values , vec . count) } , skip_list_mode : ferment_interfaces :: FFIConversion :: ffi_from (ffi_ref . skip_list_mode) , } } unsafe fn ffi_to_const (obj : LLMQSnapshot) -> * const LLMQSnapshot_FFI { ferment_interfaces :: boxed (LLMQSnapshot_FFI { member_list : ferment_interfaces :: FFIConversion :: ffi_to (obj . member_list) , skip_list : ferment_interfaces :: FFIConversion :: ffi_to (obj . skip_list) , skip_list_mode : ferment_interfaces :: FFIConversion :: ffi_to (obj . skip_list_mode) , }) } unsafe fn destroy (ffi : * mut LLMQSnapshot_FFI) { ferment_interfaces :: unbox_any (ffi) ; } } impl Drop for LLMQSnapshot_FFI { fn drop (& mut self) { unsafe { let ffi_ref = self ; ferment_interfaces :: unbox_any (ffi_ref . member_list) ; ; ferment_interfaces :: unbox_any (ffi_ref . skip_list) ; ; < LLMQSnapshotSkipMode_FFI as ferment_interfaces :: FFIConversion < LLMQSnapshotSkipMode >> :: destroy (ffi_ref . skip_list_mode) ; } } } # [allow (non_snake_case)] # [no_mangle] pub unsafe extern "C" fn LLMQSnapshot_FFI_destroy (ffi : * mut LLMQSnapshot_FFI) { ferment_interfaces :: unbox_any (ffi) ; } } } # [doc = "FFI-representation of the # [doc = \"FFI-representation of the SomeStruct\"]"] # [repr (C)] # [derive (Clone)] # [allow (non_camel_case_types)] pub struct SomeStruct_FFI { pub name : * mut std :: os :: raw :: c_char , } impl ferment_interfaces :: FFIConversion < SomeStruct > for SomeStruct_FFI { unsafe fn ffi_from_const (ffi : * const SomeStruct_FFI) -> SomeStruct { let ffi_ref = & * ffi ; SomeStruct { name : ferment_interfaces :: FFIConversion :: ffi_from (ffi_ref . name) , } } unsafe fn ffi_to_const (obj : SomeStruct) -> * const SomeStruct_FFI { ferment_interfaces :: boxed (SomeStruct_FFI { name : ferment_interfaces :: FFIConversion :: ffi_to (obj . name) , }) } unsafe fn destroy (ffi : * mut SomeStruct_FFI) { ferment_interfaces :: unbox_any (ffi) ; } } impl Drop for SomeStruct_FFI { fn drop (& mut self) { unsafe { let ffi_ref = self ; < std :: os :: raw :: c_char as ferment_interfaces :: FFIConversion < String >> :: destroy (ffi_ref . name) ; } } } # [allow (non_snake_case)] # [no_mangle] pub unsafe extern "C" fn SomeStruct_FFI_destroy (ffi : * mut SomeStruct_FFI) { ferment_interfaces :: unbox_any (ffi) ; } pub mod some_package { use ferment_example :: nested :: HashID ; use crate :: fermented :: types :: model :: snapshot :: LLMQSnapshot_FFI ; use ferment_example :: fermented :: types :: nested :: HashID_FFI ; use crate :: some_package :: get_hash_id_form_snapshot ; use crate :: model :: snapshot :: LLMQSnapshot ; # [doc = "FFI-representation of the get_hash_id_form_snapshot"] # [doc = r" # Safety"] # [no_mangle] pub unsafe extern "C" fn ffi_get_hash_id_form_snapshot (_snapshot : * mut LLMQSnapshot_FFI ,) -> * mut HashID_FFI { let obj = get_hash_id_form_snapshot (ferment_interfaces :: FFIConversion :: ffi_from (_snapshot) ,) ; ferment_interfaces :: FFIConversion :: ffi_to (obj) } } } # [allow (clippy :: all , dead_code , redundant_semicolons , unused_braces , unused_imports , unused_unsafe , unused_variables)] pub mod generics { # [repr (C)] # [derive (Clone)] # [allow (non_camel_case_types)] pub struct Vec_i32_FFI { pub count : usize , pub values : * mut i32 , } impl ferment_interfaces :: FFIConversion < Vec < i32 >> for Vec_i32_FFI { unsafe fn ffi_from_const (ffi : * const Vec_i32_FFI) -> Vec < i32 > { let ffi_ref = & * ffi ; ferment_interfaces :: FFIVecConversion :: decode (ffi_ref) } unsafe fn ffi_to_const (obj : Vec < i32 >) -> * const Vec_i32_FFI { ferment_interfaces :: FFIVecConversion :: encode (obj) } unsafe fn destroy (ffi : * mut Vec_i32_FFI) { ferment_interfaces :: unbox_any (ffi) ; } } impl ferment_interfaces :: FFIVecConversion for Vec_i32_FFI { type Value = i32 ; unsafe fn decode (& self) -> Vec < Self :: Value > { ferment_interfaces :: from_simple_vec (self . values as * const Self :: Value , self . count) } unsafe fn encode (obj : Vec < Self :: Value >) -> * mut Self { ferment_interfaces :: boxed (Self { count : obj . len () , values : ferment_interfaces :: boxed_vec (obj) }) } } impl Drop for Vec_i32_FFI { fn drop (& mut self) { unsafe { ferment_interfaces :: unbox_vec_ptr (self . values , self . count) ; } } } # [repr (C)] # [derive (Clone)] # [allow (non_camel_case_types)] pub struct Vec_u8_FFI { pub count : usize , pub values : * mut u8 , } impl ferment_interfaces :: FFIConversion < Vec < u8 >> for Vec_u8_FFI { unsafe fn ffi_from_const (ffi : * const Vec_u8_FFI) -> Vec < u8 > { let ffi_ref = & * ffi ; ferment_interfaces :: FFIVecConversion :: decode (ffi_ref) } unsafe fn ffi_to_const (obj : Vec < u8 >) -> * const Vec_u8_FFI { ferment_interfaces :: FFIVecConversion :: encode (obj) } unsafe fn destroy (ffi : * mut Vec_u8_FFI) { ferment_interfaces :: unbox_any (ffi) ; } } impl ferment_interfaces :: FFIVecConversion for Vec_u8_FFI { type Value = u8 ; unsafe fn decode (& self) -> Vec < Self :: Value > { ferment_interfaces :: from_simple_vec (self . values as * const Self :: Value , self . count) } unsafe fn encode (obj : Vec < Self :: Value >) -> * mut Self { ferment_interfaces :: boxed (Self { count : obj . len () , values : ferment_interfaces :: boxed_vec (obj) }) } } impl Drop for Vec_u8_FFI { fn drop (& mut self) { unsafe { ferment_interfaces :: unbox_vec_ptr (self . values , self . count) ; } } } }