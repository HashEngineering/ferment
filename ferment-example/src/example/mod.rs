use std::collections::BTreeMap;
//use dpp::identity::{Identity, IdentityPublicKey, IdentityV0, KeyID};
//use dpp::identity::identity_public_key::v0::IdentityPublicKeyV0;
//use dpp::version::LATEST_PLATFORM_VERSION;
//use platform_value::Identifier;
//use rand::SeedableRng;

pub mod address;

// #[ferment_macro::export]
// pub fn dpp_get_identity(identifier: &Identifier) -> Identity {
//
//     let id = Identifier::from_bytes(&identifier.as_slice()).expect("parse identity id");
//
//     let mut keys: BTreeMap<KeyID, IdentityPublicKey> = BTreeMap::new();
//     let mut rng = rand::rngs::StdRng::from_entropy();
//
//     let key: IdentityPublicKey = IdentityPublicKey::default_versioned(LATEST_PLATFORM_VERSION)
//             .expect("expected a random key");
//
//     let key2: IdentityPublicKey = IdentityPublicKey::default_versioned(LATEST_PLATFORM_VERSION)
//             .expect("expected a random key");
//
//     keys.insert(1, key);
//     keys.insert(2, key2);
//
//     let identity = IdentityV0 {
//         id,
//         public_keys: keys,
//         balance: 2,
//         revision: 1,
//     };
//     Identity::V0(identity)
// }