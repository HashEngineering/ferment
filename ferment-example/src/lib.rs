// mod chain;
pub mod example;
pub mod fermented;
// mod traits;
// mod asyn;
#[allow(dead_code)]
pub mod identity;
pub mod types;

extern crate ferment_macro;
extern crate tokio;

use std::time::Duration;
use std::error::Error;
use std::fmt::{Debug, Display, Formatter};

#[allow(non_camel_case_types)]
#[ferment_macro::register(Duration)]
pub struct Duration_FFI {
    secs: u64,
    nanos: u32,
}
ferment_interfaces::impl_custom_conversion!(Duration, Duration_FFI,
    |value: &Duration_FFI| Duration::new(value.secs, value.nanos),
    |value: &Duration| Self { secs: value.as_secs(), nanos: value.subsec_nanos() }
);

#[allow(non_camel_case_types)]
#[ferment_macro::register(Error)]
#[derive(Debug)]
pub struct std_error_Error_FFI {

}

impl Display for std_error_Error_FFI {
    fn fmt(&self, _f: &mut Formatter<'_>) -> std::fmt::Result {
        todo!()
    }
}

impl Error for std_error_Error_FFI {}

// impl From<&std_error_Error_FFI> for dyn Error where Self: Sized {
//     fn from(value: &std_error_Error_FFI) -> Self {
//         value
//     }
// }
// impl From<dyn Error> for std_error_Error_FFI {
//     fn from(value: &dyn Error) -> Self {
//         value.into()
//     }
// }
// impl ferment_interfaces::FFIConversion<std_error_Error_FFI> for dyn Error where Self: Sized {
//     unsafe fn ffi_from_const(ffi: *const Self) -> std_error_Error_FFI {
//         Error::from(&*ffi)
//     }
//     unsafe fn ffi_to_const(obj: dyn Error) -> *const Self {
//         ferment_interfaces::boxed(<std_error_Error_FFI>::from(&obj))
//     }
// }


#[ferment_macro::export]
pub struct RootStruct {
    pub name: String,
}

pub struct ExcludedStruct {
    pub name: String,
}

#[ferment_macro::export]
pub fn get_root_struct() -> RootStruct {
    RootStruct { name: format!("Root") }
}

pub mod nested {
    use crate::RootStruct;

    #[ferment_macro::export]
    pub fn get_root_struct_2() -> RootStruct {
        RootStruct { name: format!("Root") }
    }

    #[ferment_macro::export]
    pub struct RootUser {
        pub root: RootStruct
    }

    // use crate::asyn::query::{AppliedRequestSettings, Query, RequestSettings, TransportClient, TransportRequest};

    //     use std::collections::BTreeMap;
//     use ferment_interfaces::OpaqueContext;
//
//     #[ferment_macro::export]
//     pub type KeyID = u32;
//
//     #[ferment_macro::export]
//     pub type Hash160 = [u8; 20];
//
    #[ferment_macro::export]
    pub type HashID = [u8; 32];

//     #[ferment_macro::export]
//     pub type UsedKeyMatrix = Vec<bool>;
//
//     #[ferment_macro::export]
//     pub type UsedStruct = HashID;
//
//     #[ferment_macro::export]
//     pub type ArrayOfArraysOfHashes = Vec<Vec<HashID>>;
//
//     #[ferment_macro::export]
//     pub type MapOfHashes = BTreeMap<HashID, HashID>;
//
//     #[ferment_macro::export]
//     pub type MapOfVecHashes = BTreeMap<HashID, Vec<HashID>>;
//
    #[ferment_macro::export]
    #[derive(Clone)]
    pub struct BinaryData(pub Vec<u8>);

    impl From<Vec<u8>> for BinaryData {
        fn from(value: Vec<u8>) -> Self {
            BinaryData::new(value)
        }
    }

    impl BinaryData {
        pub fn new(buffer: Vec<u8>) -> BinaryData {
            BinaryData(buffer)
        }
    }
//
//     #[ferment_macro::export]
//     pub struct SimpleData(pub Vec<u32>);
//
    #[derive(Clone, Debug)]
    #[ferment_macro::export]
    pub struct IdentifierBytes32(pub [u8; 32]);
//
//     #[ferment_macro::export]
//     pub struct UnnamedPair(pub [u8; 32], pub u32);
//
    #[derive(Clone, Debug)]
    #[ferment_macro::export]
    pub struct Identifier(pub IdentifierBytes32);
    //#[ferment_macro::export]
    impl Identifier {
        pub fn new(buffer: [u8; 32]) -> Identifier {
            Identifier(IdentifierBytes32(buffer))
        }


        pub fn as_bytes(&self) -> &[u8; 32] {
            &self.0.0
        }

        pub fn as_slice(&self) -> &[u8] {
            self.0.0.as_slice()
        }

        // TODO the constructor "From" shouldn't use the reference to collection
        pub fn from_bytes(bytes: &[u8]) -> Result<Identifier, Error> {
            if bytes.len() != 32 {
                return Err(Error::ByteLengthNot32BytesError(String::from(
                    "Identifier must be 32 bytes long",
                )));
            }

            // Since we checked that vector size is 32, we can use unwrap
            Ok(Identifier::new(bytes.try_into().unwrap()))
        }


        pub fn len(&self) -> usize {
            32
        }

        pub fn is_empty(&self) -> bool {
            false
        }

        // TODO - consider to change the name to 'asBuffer`
        pub fn to_buffer(&self) -> [u8; 32] {
            self.0.0
        }

        pub fn into_buffer(self) -> [u8; 32] {
            self.0.0
        }

        /// Convenience method to get underlying buffer as a vec
        pub fn to_vec(&self) -> Vec<u8> {
            self.0.0.to_vec()
        }
    }

    #[derive(Clone, Eq, PartialEq, Debug)]
    pub enum Error {
        Unsupported(String),

        StructureError(String),

        PathError(String),

        IntegerSizeError,

        IntegerParsingError,

        StringDecodingError(String),

        KeyMustBeAString,

        ByteLengthNot20BytesError(String),

        ByteLengthNot32BytesError(String),

        ByteLengthNot36BytesError(String),

        SerdeSerializationError(String),

        SerdeDeserializationError(String),

        UnknownVersionMismatch,
    }
//
//     #[ferment_macro::export]
//     pub enum TestEnum {
//         Variant1(String),
//         Variant2,
//         Variant3(HashID, u32),
//         Variant4(HashID, u32, String),
//         Variant5(BTreeMap<String, HashID>, u32, String),
//     }
//
    #[ferment_macro::export]
    #[derive(Debug)]
    pub struct DataContractNotPresentError {
        pub data_contract_id: Identifier,
    }
    #[ferment_macro::export]
    pub type FeatureVersion = u16;
    #[ferment_macro::export]
    pub type OptionalFeatureVersion = Option<u16>; //This is a feature that didn't always exist

    #[derive(Clone, Debug, Default)]
    #[ferment_macro::export]
    pub struct FeatureVersionBounds {
        pub min_version: FeatureVersion,
        pub max_version: FeatureVersion,
        pub default_current_version: FeatureVersion,
    }

    #[derive(Clone, Debug)]
    #[ferment_macro::export]
    pub struct PlatformVersion {
        pub protocol_version: u32,
        pub identity: FeatureVersionBounds,
        pub proofs: FeatureVersionBounds,
    }

    #[ferment_macro::export]
    #[derive(Debug)]
    pub enum ProtocolError {
        IdentifierError(String),
        StringDecodeError(String),
        StringDecodeError2(String, u32),
        EmptyPublicKeyDataError,
        MaxEncodedBytesReachedError {
            max_size_kbytes: usize,
            size_hit: usize,
        },
        EncodingError(String),
        EncodingError2(&'static str),
        DataContractNotPresentError(DataContractNotPresentError),
        UnknownVersionMismatch {
            method: String,
            known_versions: Vec<FeatureVersion>,
            received: FeatureVersion,
        },
    }
//
//     #[ferment_macro::export]
//     pub type AddInsightCallback = fn(block_hash: HashID, context: OpaqueContext);
//
//     #[ferment_macro::export]
//     pub type ShouldProcessDiffWithRangeCallback = fn(
//         base_block_hash: HashID,
//         block_hash: HashID,
//         context: OpaqueContext,
//     ) -> ProtocolError;
//
//     #[ferment_macro::export]
//     pub fn find_hash_by_u32(key: u32, map: BTreeMap<u32, HashID>) -> Option<HashID> {
//         map.get(&key).copied()
//     }
//
//     #[ferment_macro::export]
//     pub struct TestStruct {
//         pub vec_u8: Vec<u8>,
//         pub vec_u32: Vec<u32>,
//         pub vec_vec_u32: Vec<Vec<u32>>,
//         pub map_key_simple_value_simple: BTreeMap<u32, u32>,
//         pub map_key_simple_value_complex: BTreeMap<u32, HashID>,
//         pub map_key_simple_value_vec_simple: BTreeMap<u32, Vec<u32>>,
//         pub map_key_simple_value_vec_complex: BTreeMap<u32, Vec<HashID>>,
//         pub map_key_simple_value_map_key_simple_value_simple: BTreeMap<u32, BTreeMap<u32, u32>>,
//         pub map_key_simple_value_map_key_simple_value_complex: BTreeMap<u32, BTreeMap<u32, HashID>>,
//         pub map_key_simple_value_map_key_simple_value_vec_simple:
//             BTreeMap<u32, BTreeMap<u32, Vec<u32>>>,
//         pub map_key_simple_value_map_key_simple_value_vec_complex:
//             BTreeMap<u32, BTreeMap<u32, Vec<HashID>>>,
//
//         pub map_key_complex_value_simple: BTreeMap<HashID, u32>,
//         pub map_key_complex_value_complex: BTreeMap<HashID, HashID>,
//         pub map_key_complex_value_vec_simple: BTreeMap<HashID, Vec<u32>>,
//         pub map_key_complex_value_vec_complex: BTreeMap<HashID, Vec<HashID>>,
//
//         pub map_key_complex_value_map_key_simple_value_vec_simple:
//             BTreeMap<HashID, BTreeMap<u32, Vec<u32>>>,
//         pub map_key_complex_value_map_key_simple_value_vec_complex:
//             BTreeMap<HashID, BTreeMap<u32, Vec<HashID>>>,
//
//         pub map_key_complex_value_map_key_simple_value_map_key_complex_value_complex:
//             BTreeMap<HashID, BTreeMap<u32, BTreeMap<HashID, HashID>>>,
//
//         pub opt_primitive: Option<u8>,
//         pub opt_string: Option<String>,
//         // pub opt_str: Option<&'static str>,
//         pub opt_vec_primitive: Option<Vec<u8>>,
//         pub opt_vec_string: Option<Vec<String>>,
//         pub opt_vec_complex: Option<Vec<HashID>>,
//         pub opt_vec_vec_complex: Option<Vec<Vec<HashID>>>,
//     }
//
//

    // #[derive(Clone)]
    // pub struct GetDocumentsRequest { pub version: u32 }
    // #[derive(Clone)]
    // pub struct DocumentQuery { pub version: u32 }
    //
    // impl TransportRequest for DocumentQuery {
    //     type Client = <GetDocumentsRequest as TransportRequest>::Client;
    //     type Response = <GetDocumentsRequest as TransportRequest>::Response;
    //     const SETTINGS_OVERRIDES: RequestSettings = RequestSettings { timeout: None, retries: None };
    //
    //     fn execute_transport(
    //         self,
    //         client: &mut Self::Client,
    //         settings: &AppliedRequestSettings,
    //     ) -> Result<Self::Response, <Self::Client as TransportClient>::Error> {}
    // }
    // impl Query<GetDocumentsRequest> for Identifier {
    //     fn query(self, prove: bool) -> Result<GetDocumentsRequest, ProtocolError> {
    //         Ok(GetDocumentsRequest { version: u32::from(prove) })
    //     }
    // }

}

