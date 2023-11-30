mod chain;
mod example;
use rand::rngs::StdRng;
use crate::nested::get_identity2;

pub mod fermented;
mod traits;

extern crate ferment_macro;
extern crate core;

#[ferment_macro::export]
pub struct RootStruct {
    pub name: String,
}

pub struct ExcludedStruct {
    pub name: String,
}

pub mod nested {
    use dashcore::secp256k1::rand::rngs::StdRng as EcdsaRng;
    use std::collections::BTreeMap;
    use dashcore::Network;
    use dashcore::secp256k1::Secp256k1;
    use dashcore::signer::ripemd160_sha256;
    //use platform_value::Identifier;
    use rand::rngs::StdRng;
    use rand::SeedableRng;
    //use rs_sdk::platform::Identifier;

    #[ferment_macro::export]
    pub type KeyID = u32;

    #[ferment_macro::export]
    pub type Hash160 = [u8; 20];

    #[ferment_macro::export]
    pub type HashID = [u8; 32];

    #[ferment_macro::export]
    pub type UsedKeyMatrix = Vec<bool>;

    #[ferment_macro::export]
    pub type UsedStruct = HashID;

    #[ferment_macro::export]
    pub type ArrayOfArraysOfHashes = Vec<Vec<HashID>>;

    #[ferment_macro::export]
    pub type MapOfHashes = BTreeMap<HashID, HashID>;

    #[ferment_macro::export]
    pub type MapOfVecHashes = BTreeMap<HashID, Vec<HashID>>;

    #[derive(
    Default,
    Debug,
    Clone,
    PartialEq,
    Eq,
    Ord,
    PartialOrd,
    Hash,
    )]
    #[ferment_macro::export]
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

    #[ferment_macro::export]
    pub struct SimpleData(pub Vec<u32>);

    #[derive(
    Default,
    Debug,
    Clone,
    PartialEq,
    Eq,
    Hash,
    Ord,
    PartialOrd,
    Copy
    )]
    #[ferment_macro::export]
    pub struct IdentifierBytes32(pub [u8; 32]);

    #[ferment_macro::export]
    pub struct UnnamedPair(pub [u8; 32], pub u32);

    #[derive(
    Default,
    Debug,
    Clone,
    PartialEq,
    Eq,
    Hash,
    Ord,
    PartialOrd
    )]
    #[ferment_macro::export]
    pub struct Identifier(pub IdentifierBytes32);

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
    }

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

    #[ferment_macro::export]
    pub enum TestEnum {
        Variant1(String),
        Variant2,
        Variant3(HashID, u32),
        Variant4(HashID, u32, String),
        Variant5(BTreeMap<String, HashID>, u32, String),
    }

    #[ferment_macro::export]
    pub struct DataContractNotPresentError {
        pub data_contract_id: Identifier,
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
        //DataContractNotPresentError(DataContractNotPresentError),
        UnknownVersionMismatch,
    }

    #[ferment_macro::export]
    pub type AddInsightCallback = fn(block_hash: HashID, context: ferment_interfaces::OpaqueContext);

    #[ferment_macro::export]
    pub type ShouldProcessDiffWithRangeCallback = fn(
        base_block_hash: HashID,
        block_hash: HashID,
        context: ferment_interfaces::OpaqueContext,
    ) -> ProtocolError;

    #[ferment_macro::export]
    pub fn find_hash_by_u32(key: u32, map: BTreeMap<u32, HashID>) -> Option<HashID> {
        map.get(&key).clone().copied()
    }

    #[ferment_macro::export]
    pub struct TestStruct {
        pub vec_u8: Vec<u8>,
        pub vec_u32: Vec<u32>,
        pub vec_vec_u32: Vec<Vec<u32>>,
        pub map_key_simple_value_simple: BTreeMap<u32, u32>,
        pub map_key_simple_value_complex: BTreeMap<u32, HashID>,
        pub map_key_simple_value_vec_simple: BTreeMap<u32, Vec<u32>>,
        pub map_key_simple_value_vec_complex: BTreeMap<u32, Vec<HashID>>,
        pub map_key_simple_value_map_key_simple_value_simple: BTreeMap<u32, BTreeMap<u32, u32>>,
        pub map_key_simple_value_map_key_simple_value_complex: BTreeMap<u32, BTreeMap<u32, HashID>>,
        pub map_key_simple_value_map_key_simple_value_vec_simple:
        BTreeMap<u32, BTreeMap<u32, Vec<u32>>>,
        pub map_key_simple_value_map_key_simple_value_vec_complex:
        BTreeMap<u32, BTreeMap<u32, Vec<HashID>>>,

        pub map_key_complex_value_simple: BTreeMap<HashID, u32>,
        pub map_key_complex_value_complex: BTreeMap<HashID, HashID>,
        pub map_key_complex_value_vec_simple: BTreeMap<HashID, Vec<u32>>,
        pub map_key_complex_value_vec_complex: BTreeMap<HashID, Vec<HashID>>,

        pub map_key_complex_value_map_key_simple_value_vec_simple:
        BTreeMap<HashID, BTreeMap<u32, Vec<u32>>>,
        pub map_key_complex_value_map_key_simple_value_vec_complex:
        BTreeMap<HashID, BTreeMap<u32, Vec<HashID>>>,

        pub map_key_complex_value_map_key_simple_value_map_key_complex_value_complex:
        BTreeMap<HashID, BTreeMap<u32, BTreeMap<HashID, HashID>>>,
    }


    #[derive(
    Debug,
    PartialEq,
    Eq,
    Clone,
    Copy,
    Hash,
    Ord,
    PartialOrd,
    )]
    #[ferment_macro::export]
    pub enum Purpose {
        /// at least one authentication key must be registered for all security levels
        AUTHENTICATION = 0,
        /// this key cannot be used for signing documents
        ENCRYPTION = 1,
        /// this key cannot be used for signing documents
        DECRYPTION = 2,
        /// this key cannot be used for signing documents
        WITHDRAW = 3,
        /// this key cannot be used for signing documents
        SYSTEM = 4,
        /// this key cannot be used for signing documents
        VOTING = 5,
    }

    #[derive(
    Debug,
    Clone,
    PartialEq,
    Eq,
    Ord,
    PartialOrd,
    Hash,
    )]
    #[ferment_macro::export]
    pub enum SecurityLevel {
        MASTER = 0,
        CRITICAL = 1,
        HIGH = 2,
        MEDIUM = 3,
    }

    #[derive(
    Debug,
    Clone,
    PartialEq,
    Eq,
    Ord,
    PartialOrd,
    Hash,
    )]
    #[ferment_macro::export]
    pub enum KeyType {
        ECDSA_SECP256K1 = 0,
        BLS12_381 = 1,
        ECDSA_HASH160 = 2,
        BIP13_SCRIPT_HASH = 3,
        EDDSA_25519_HASH160 = 4,
    }

    impl KeyType {
        /// Gets the default size of the public key
        pub fn random_public_and_private_key_data_v0(&self, rng: &mut StdRng) -> (Vec<u8>, Vec<u8>) {
            match self {
                KeyType::ECDSA_SECP256K1 => {
                    let secp = Secp256k1::new();
                    let mut rng = EcdsaRng::from_rng(rng).unwrap();
                    let secret_key = dashcore::secp256k1::SecretKey::new(&mut rng);
                    let private_key = dashcore::PrivateKey::new(secret_key, Network::Dash);
                    (
                        private_key.public_key(&secp).to_bytes(),
                        private_key.to_bytes(),
                    )
                }
                KeyType::BLS12_381 => {
                    panic!("BLS not supported")
                }
                KeyType::ECDSA_HASH160 => {
                    let secp = Secp256k1::new();
                    let mut rng = EcdsaRng::from_rng(rng).unwrap();
                    let secret_key = dashcore::secp256k1::SecretKey::new(&mut rng);
                    let private_key = dashcore::PrivateKey::new(secret_key, Network::Dash);
                    (
                        ripemd160_sha256(private_key.public_key(&secp).to_bytes().as_slice()).to_vec(),
                        private_key.to_bytes(),
                    )
                }
                KeyType::EDDSA_25519_HASH160 => {
                    panic!("eddsa not supported")
                }
                KeyType::BIP13_SCRIPT_HASH => {
                    panic!("bip13 not supported")
                }
            }
        }

        pub fn random_public_and_private_key_data(
            &self,
            rng: &mut StdRng,
            platform_version: i32,
        ) -> Result<(Vec<u8>, Vec<u8>), ProtocolError> {
            match platform_version
            {
                0 => Ok(self.random_public_and_private_key_data_v0(rng)),
                version => Err(ProtocolError::UnknownVersionMismatch),
            }
        }
    }

    #[ferment_macro::export]
    pub type TimestampMillis = u64;

    #[ferment_macro::export]
    pub type Revision = u64;

    #[repr(u8)]
    #[derive(
    Debug, PartialEq, Eq, Clone, Ord, PartialOrd, Hash,
    )]
    #[ferment_macro::export]
    pub enum ContractBounds {
        /// this key can only be used within a specific contract
        SingleContract(Identifier),
        /// this key can only be used within a specific contract and for a specific document type
        SingleContractDocumentType(Identifier, String),
        // /// this key can only be used within contracts owned by a specified owner
        // #[serde(rename = "multipleContractsOfSameOwner")]
        // MultipleContractsOfSameOwner { owner_id: Identifier } = 2,
    }

    #[derive(
    Debug,
    Clone,
    Eq,
    PartialEq,
    Hash,
    Ord,
    PartialOrd,
    )]
    #[ferment_macro::export]
    pub enum IdentityPublicKey {
        V0(IdentityPublicKeyV0),
    }

    #[derive(
    Debug,
    Clone,
    PartialEq,
    Eq,
    Ord,
    PartialOrd,
    Hash,
    )]
    #[ferment_macro::export]
    pub struct IdentityPublicKeyV0 {
        pub id: KeyID,
        pub purpose: Purpose,
        pub security_level: SecurityLevel,
        pub contract_bounds: Option<ContractBounds>,
        pub key_type: KeyType,
        pub read_only: bool,
        pub data: BinaryData,
        pub disabled_at: Option<TimestampMillis>,
    }

    impl IdentityPublicKeyV0 {
        pub fn random_ecdsa_master_authentication_key_with_rng(
            id: KeyID,
            rng: &mut StdRng,
            platform_version: i32,
        ) -> Result<(Self, Vec<u8>), ProtocolError> {
            let key_type = KeyType::ECDSA_SECP256K1;
            let purpose = Purpose::AUTHENTICATION;
            let security_level = SecurityLevel::MASTER;
            let read_only = false;
            let (data, private_data) =
                key_type.random_public_and_private_key_data(rng, platform_version)?;
            Ok((
                IdentityPublicKeyV0 {
                    id,
                    key_type,
                    purpose,
                    security_level,
                    read_only,
                    disabled_at: None,
                    data: data.into(),
                    contract_bounds: None,
                },
                private_data,
            ))
        }
    }

    #[ferment_macro::export]
    pub enum Identity {
        V0(IdentityV0),
    }

    #[ferment_macro::export]
    #[derive(Default, Debug, Clone, Eq, PartialEq)]
    pub struct IdentityV0 {
        pub id: Identifier,
        pub public_keys: BTreeMap<KeyID, IdentityPublicKey>,
        pub balance: u64,
        pub revision: Revision,
    }

    #[ferment_macro::export]
    pub fn get_an_identity() -> Identity {
        pub const IDENTITY_ID_BYTES: [u8; 32] = [
            65, 63, 57, 243, 204, 9, 106, 71, 187, 2, 94, 221, 190, 127, 141, 114, 137, 209, 243, 50,
            60, 215, 90, 101, 229, 15, 115, 5, 44, 117, 182, 217,
        ];
        let id = Identifier::from_bytes(&IDENTITY_ID_BYTES).expect("parse identity id");


        let identity = IdentityV0 {
            id: Default::default(),
            public_keys: Default::default(),
            balance: 2,
            revision: 1,
        };
        Identity::V0(identity)
    }

    const LATEST_PLATFORM_VERSION: i32 = 0;

    #[ferment_macro::export]
    pub fn get_identity2(identifier: Identifier) -> Identity {
        let id = Identifier::from_bytes(&identifier.as_slice()).expect("parse identity id");

        let mut keys: BTreeMap<KeyID, IdentityPublicKey> = BTreeMap::new();
        let mut rng = rand::rngs::StdRng::from_entropy();

        let key: IdentityPublicKey = IdentityPublicKey::V0(
            IdentityPublicKeyV0::random_ecdsa_master_authentication_key_with_rng(
                1,
                &mut rng,
                LATEST_PLATFORM_VERSION,
            )
                .expect("expected a random key")
                .0
        );

        let key2: IdentityPublicKey = IdentityPublicKey::V0(
            IdentityPublicKeyV0::random_ecdsa_master_authentication_key_with_rng(
                1,
                &mut rng,
                LATEST_PLATFORM_VERSION,
            )
                .expect("expected a random key")
                .0
        );

        keys.insert(1, key);
        keys.insert(2, key2);

        let identity = IdentityV0 {
            id: id,
            public_keys: keys,
            balance: 2,
            revision: 1,
        };
        Identity::V0(identity)
    }

    #[ferment_macro::export]
    pub trait IdentityFactory {
        fn get_identity(&self, identifier: Identifier) -> Identity;
    }

    #[ferment_macro::export(IdentityFactory)]
    pub struct MyIdentityFactory {
        pub first: u32
    }

    impl IdentityFactory for MyIdentityFactory {
        fn get_identity(&self, identifier: Identifier) -> Identity {
            get_identity2(identifier)
        }
    }

    #[ferment_macro::export]
    pub fn get_identity_factory() -> MyIdentityFactory {
        return MyIdentityFactory { first: 0 }
    }
}


