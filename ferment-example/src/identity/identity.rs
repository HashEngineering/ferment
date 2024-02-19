use std::collections::BTreeMap;
use crate::nested::{BinaryData, Identifier, IdentifierBytes32, PlatformVersion, ProtocolError};
use dashcore::secp256k1::rand::rngs::StdRng as EcdsaRng;
use dashcore::Network;
use dashcore::secp256k1::Secp256k1;
use dashcore::signer::ripemd160_sha256;
use rand::rngs::StdRng;
use rand::SeedableRng;
use crate::fermented::types::nested::FeatureVersion;

#[ferment_macro::export]
pub type KeyID = u32;
#[ferment_macro::export]
pub type TimestampMillis = u64;
#[ferment_macro::export]
pub type Revision = u64;

#[ferment_macro::export]
#[derive(Clone)]
pub enum Purpose {
    AUTHENTICATION = 0,
    ENCRYPTION = 1,
    DECRYPTION = 2,
    WITHDRAW = 3,
    SYSTEM = 4,
    VOTING = 5,
}
#[ferment_macro::export]
#[derive(Clone)]
pub enum SecurityLevel {
    MASTER = 0,
    CRITICAL = 1,
    HIGH = 2,
    MEDIUM = 3,
}

#[ferment_macro::export]
#[derive(Clone)]
pub enum Identity {
    V0(IdentityV0),
}

#[ferment_macro::export]
#[derive(Clone)]
pub enum IdentityPublicKey {
    V0(IdentityPublicKeyV0),
}


#[ferment_macro::export]
#[derive(Clone)]
pub enum ContractBounds {
    SingleContract { id: Identifier },
    SingleContractDocumentType  {
        id: Identifier,
        document_type_name: String,
    },
    // SingleContract { id: Identifier } = 0,
    // SingleContractDocumentType {
    //     id: Identifier,
    //     document_type_name: String,
    // } = 1,
}

#[allow(non_camel_case_types)]
#[ferment_macro::export]
#[derive(Clone)]
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
            version => Err(ProtocolError::UnknownVersionMismatch { method: "random_public_and_private_key_data".to_string(), known_versions: vec![0], received:  version as u16 }),
        }
    }
}

#[ferment_macro::export]
#[derive(Clone)]
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


#[ferment_macro::export]
#[derive(Clone)]
pub struct IdentityV0 {
    pub id: Identifier,
    pub public_keys: BTreeMap<KeyID, IdentityPublicKey>,
    pub balance: u64,
    pub revision: Revision,
}

#[ferment_macro::export]
impl Identity {
    pub fn create_basic_identity(
        id: [u8; 32],
        _platform_version: &PlatformVersion,
    ) -> Result<Self, ProtocolError> {
        Ok(Self::create_basic_identity_v0(id))
    }
    pub fn create_basic_identity_v0(id: [u8; 32]) -> Self {
        Identity::V0(IdentityV0 {
            id: Identifier(IdentifierBytes32(id)),
            revision: 0,
            balance: 0,
            public_keys: BTreeMap::new(),
        })
    }
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
pub fn get_an_identity() -> Identity {
    pub const IDENTITY_ID_BYTES: [u8; 32] = [
        65, 63, 57, 243, 204, 9, 106, 71, 187, 2, 94, 221, 190, 127, 141, 114, 137, 209, 243, 50,
        60, 215, 90, 101, 229, 15, 115, 5, 44, 117, 182, 217,
    ];
    let id = Identifier::from_bytes(&IDENTITY_ID_BYTES).expect("parse identity id");


    let identity = IdentityV0 {
        id,
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