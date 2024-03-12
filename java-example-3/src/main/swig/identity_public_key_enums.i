%ignore purpose_AUTHENTICATION_ctor();
%rename(Purpose) crate_identity_identity_Purpose;
%rename(KeyType) crate_identity_identity_KeyType;
%rename(SecurityLevel) crate_identity_identity_SecurityLevel;

%rename(MEDIUM) crate_identity_identity_SecurityLevel_MEDIUM;
%rename(MASTER) crate_identity_identity_SecurityLevel_MASTER;
%rename(CRITICAL) crate_identity_identity_SecurityLevel_CRITICAL;
%rename(HIGH) crate_identity_identity_SecurityLevel_HIGH;

%rename(AUTHENTICATION) crate_identity_identity_Purpose_AUTHENTICATION;
%rename(ENCRYPTION) crate_identity_identity_Purpose_ENCRYPTION;
%rename(DECRYPTION) crate_identity_identity_Purpose_DECRYPTION;
%rename(WITHDRAW) crate_identity_identity_Purpose_WITHDRAW;
%rename(SYSTEM) crate_identity_identity_Purpose_SYSTEM;
%rename(VOTING) crate_identity_identity_Purpose_VOTING;

%rename (ECDSA_SECP256K1) crate_identity_identity_KeyType_ECDSA_SECP256K1;
%rename (BLS12_381) crate_identity_identity_KeyType_BLS12_381;
%rename (ECDSA_HASH160) crate_identity_identity_KeyType_ECDSA_HASH160;
%rename (BIP13_SCRIPT_HASH) crate_identity_identity_KeyType_BIP13_SCRIPT_HASH;
%rename (EDDSA_25519_HASH160) crate_identity_identity_KeyType_EDDSA_25519_HASH160;