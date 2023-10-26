/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class exampleJNI {
  public final static native int KeyType_FFI_ECDSA_SECP256K1_get();
  public final static native int KeyType_FFI_BLS12_381_get();
  public final static native int KeyType_FFI_ECDSA_HASH160_get();
  public final static native int KeyType_FFI_BIP13_SCRIPT_HASH_get();
  public final static native int KeyType_FFI_EDDSA_25519_HASH160_get();
  public final static native int Purpose_FFI_AUTHENTICATION_get();
  public final static native int Purpose_FFI_ENCRYPTION_get();
  public final static native int Purpose_FFI_DECRYPTION_get();
  public final static native int Purpose_FFI_WITHDRAW_get();
  public final static native int Purpose_FFI_SYSTEM_get();
  public final static native int Purpose_FFI_VOTING_get();
  public final static native int SecurityLevel_FFI_MASTER_get();
  public final static native int SecurityLevel_FFI_CRITICAL_get();
  public final static native int SecurityLevel_FFI_HIGH_get();
  public final static native int SecurityLevel_FFI_MEDIUM_get();
  public final static native void IdentifierBytes32__0_set(long jarg1, IdentifierBytes32 jarg1_, byte[] jarg2);
  public final static native byte[] IdentifierBytes32__0_get(long jarg1, IdentifierBytes32 jarg1_);
  public final static native long new_IdentifierBytes32(short[] jarg1);
  public final static native void delete_IdentifierBytes32(long jarg1);
  public final static native void Identifier__0_set(long jarg1, Identifier jarg1_, long jarg2, IdentifierBytes32 jarg2_);
  public final static native long Identifier__0_get(long jarg1, Identifier jarg1_);
  public final static native long new_Identifier();
  public final static native void delete_Identifier(long jarg1);
  public final static native void KeyID__0_set(long jarg1, KeyID jarg1_, long jarg2);
  public final static native long KeyID__0_get(long jarg1, KeyID jarg1_);
  public final static native long new_KeyID();
  public final static native void delete_KeyID(long jarg1);
  public final static native void ContractBounds_FFI_SingleContractDocumentType_Body__0_set(long jarg1, ContractBounds_FFI_SingleContractDocumentType_Body jarg1_, long jarg2, Identifier jarg2_);
  public final static native long ContractBounds_FFI_SingleContractDocumentType_Body__0_get(long jarg1, ContractBounds_FFI_SingleContractDocumentType_Body jarg1_);
  public final static native void ContractBounds_FFI_SingleContractDocumentType_Body__1_set(long jarg1, ContractBounds_FFI_SingleContractDocumentType_Body jarg1_, String jarg2);
  public final static native String ContractBounds_FFI_SingleContractDocumentType_Body__1_get(long jarg1, ContractBounds_FFI_SingleContractDocumentType_Body jarg1_);
  public final static native long new_ContractBounds_FFI_SingleContractDocumentType_Body();
  public final static native void delete_ContractBounds_FFI_SingleContractDocumentType_Body(long jarg1);
  public final static native void ContractBounds_FFI_tag_set(long jarg1, ContractBounds_FFI jarg1_, int jarg2);
  public final static native int ContractBounds_FFI_tag_get(long jarg1, ContractBounds_FFI jarg1_);
  public final static native void ContractBounds_FFI_single_contract_set(long jarg1, ContractBounds_FFI jarg1_, long jarg2, Identifier jarg2_);
  public final static native long ContractBounds_FFI_single_contract_get(long jarg1, ContractBounds_FFI jarg1_);
  public final static native void ContractBounds_FFI_single_contract_document_type_set(long jarg1, ContractBounds_FFI jarg1_, long jarg2, ContractBounds_FFI_SingleContractDocumentType_Body jarg2_);
  public final static native long ContractBounds_FFI_single_contract_document_type_get(long jarg1, ContractBounds_FFI jarg1_);
  public final static native long new_ContractBounds_FFI();
  public final static native void delete_ContractBounds_FFI(long jarg1);
  public final static native void BinaryData__0_set(long jarg1, BinaryData jarg1_, byte[] jarg2);
  public final static native byte[] BinaryData__0_get(long jarg1, BinaryData jarg1_);
  public final static native long new_BinaryData();
  public final static native void delete_BinaryData(long jarg1);
  public final static native void TimestampMillis_FFI__0_set(long jarg1, TimestampMillis_FFI jarg1_, java.math.BigInteger jarg2);
  public final static native java.math.BigInteger TimestampMillis_FFI__0_get(long jarg1, TimestampMillis_FFI jarg1_);
  public final static native long new_TimestampMillis_FFI();
  public final static native void delete_TimestampMillis_FFI(long jarg1);
  public final static native void IdentityPublicKeyV0_id_set(long jarg1, IdentityPublicKeyV0 jarg1_, long jarg2, KeyID jarg2_);
  public final static native long IdentityPublicKeyV0_id_get(long jarg1, IdentityPublicKeyV0 jarg1_);
  public final static native void IdentityPublicKeyV0_security_level_set(long jarg1, IdentityPublicKeyV0 jarg1_, long jarg2);
  public final static native long IdentityPublicKeyV0_security_level_get(long jarg1, IdentityPublicKeyV0 jarg1_);
  public final static native void IdentityPublicKeyV0_contract_bounds_set(long jarg1, IdentityPublicKeyV0 jarg1_, long jarg2, ContractBounds_FFI jarg2_);
  public final static native long IdentityPublicKeyV0_contract_bounds_get(long jarg1, IdentityPublicKeyV0 jarg1_);
  public final static native void IdentityPublicKeyV0_key_type_set(long jarg1, IdentityPublicKeyV0 jarg1_, long jarg2);
  public final static native long IdentityPublicKeyV0_key_type_get(long jarg1, IdentityPublicKeyV0 jarg1_);
  public final static native void IdentityPublicKeyV0_read_only_set(long jarg1, IdentityPublicKeyV0 jarg1_, boolean jarg2);
  public final static native boolean IdentityPublicKeyV0_read_only_get(long jarg1, IdentityPublicKeyV0 jarg1_);
  public final static native void IdentityPublicKeyV0_data_set(long jarg1, IdentityPublicKeyV0 jarg1_, long jarg2, BinaryData jarg2_);
  public final static native long IdentityPublicKeyV0_data_get(long jarg1, IdentityPublicKeyV0 jarg1_);
  public final static native void IdentityPublicKeyV0_disabled_at_set(long jarg1, IdentityPublicKeyV0 jarg1_, long jarg2, TimestampMillis_FFI jarg2_);
  public final static native long IdentityPublicKeyV0_disabled_at_get(long jarg1, IdentityPublicKeyV0 jarg1_);
  public final static native int IdentityPublicKeyV0_getPurpose(long jarg1, IdentityPublicKeyV0 jarg1_);
  public final static native void IdentityPublicKeyV0_setPurpose(long jarg1, IdentityPublicKeyV0 jarg1_, int jarg2);
  public final static native long new_IdentityPublicKeyV0();
  public final static native void delete_IdentityPublicKeyV0(long jarg1);
  public final static native void IdentityPublicKey_tag_set(long jarg1, IdentityPublicKey jarg1_, int jarg2);
  public final static native int IdentityPublicKey_tag_get(long jarg1, IdentityPublicKey jarg1_);
  public final static native void IdentityPublicKey_v0_set(long jarg1, IdentityPublicKey jarg1_, long jarg2, IdentityPublicKeyV0 jarg2_);
  public final static native long IdentityPublicKey_v0_get(long jarg1, IdentityPublicKey jarg1_);
  public final static native long new_IdentityPublicKey();
  public final static native void delete_IdentityPublicKey(long jarg1);
  public final static native void std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_count_set(long jarg1, std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI jarg1_, long jarg2);
  public final static native long std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_count_get(long jarg1, std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI jarg1_);
  public final static native void std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_keys_set(long jarg1, std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI jarg1_, long jarg2);
  public final static native long std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_keys_get(long jarg1, std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI jarg1_);
  public final static native void std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_values_set(long jarg1, std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI jarg1_, long jarg2);
  public final static native long std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_values_get(long jarg1, std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI jarg1_);
  public final static native long new_std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI();
  public final static native void delete_std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI(long jarg1);
  public final static native void Revision__0_set(long jarg1, Revision jarg1_, java.math.BigInteger jarg2);
  public final static native java.math.BigInteger Revision__0_get(long jarg1, Revision jarg1_);
  public final static native long new_Revision();
  public final static native void delete_Revision(long jarg1);
  public final static native void IdentityV0_id_set(long jarg1, IdentityV0 jarg1_, long jarg2, Identifier jarg2_);
  public final static native long IdentityV0_id_get(long jarg1, IdentityV0 jarg1_);
  public final static native void IdentityV0_public_keys_set(long jarg1, IdentityV0 jarg1_, long jarg2, std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI jarg2_);
  public final static native long IdentityV0_public_keys_get(long jarg1, IdentityV0 jarg1_);
  public final static native void IdentityV0_balance_set(long jarg1, IdentityV0 jarg1_, java.math.BigInteger jarg2);
  public final static native java.math.BigInteger IdentityV0_balance_get(long jarg1, IdentityV0 jarg1_);
  public final static native void IdentityV0_revision_set(long jarg1, IdentityV0 jarg1_, long jarg2, Revision jarg2_);
  public final static native long IdentityV0_revision_get(long jarg1, IdentityV0 jarg1_);
  public final static native long IdentityV0_getPublicKey(long jarg1, IdentityV0 jarg1_, long jarg2);
  public final static native long IdentityV0_getPublicKeyById(long jarg1, IdentityV0 jarg1_, long jarg2);
  public final static native long new_IdentityV0();
  public final static native void delete_IdentityV0(long jarg1);
  public final static native void Identity_tag_set(long jarg1, Identity jarg1_, int jarg2);
  public final static native int Identity_tag_get(long jarg1, Identity jarg1_);
  public final static native void Identity_v0_set(long jarg1, Identity jarg1_, long jarg2, IdentityV0 jarg2_);
  public final static native long Identity_v0_get(long jarg1, Identity jarg1_);
  public final static native long new_Identity();
  public final static native void delete_Identity(long jarg1);
  public final static native void HashID__0_set(long jarg1, HashID jarg1_, byte[] jarg2);
  public final static native byte[] HashID__0_get(long jarg1, HashID jarg1_);
  public final static native long new_HashID();
  public final static native void delete_HashID(long jarg1);
  public final static native void std_collections_Map_keys_u32_values_crate_nested_HashID_FFI_count_set(long jarg1, std_collections_Map_keys_u32_values_crate_nested_HashID_FFI jarg1_, long jarg2);
  public final static native long std_collections_Map_keys_u32_values_crate_nested_HashID_FFI_count_get(long jarg1, std_collections_Map_keys_u32_values_crate_nested_HashID_FFI jarg1_);
  public final static native void std_collections_Map_keys_u32_values_crate_nested_HashID_FFI_keys_set(long jarg1, std_collections_Map_keys_u32_values_crate_nested_HashID_FFI jarg1_, long jarg2);
  public final static native long std_collections_Map_keys_u32_values_crate_nested_HashID_FFI_keys_get(long jarg1, std_collections_Map_keys_u32_values_crate_nested_HashID_FFI jarg1_);
  public final static native void std_collections_Map_keys_u32_values_crate_nested_HashID_FFI_values_set(long jarg1, std_collections_Map_keys_u32_values_crate_nested_HashID_FFI jarg1_, long jarg2);
  public final static native long std_collections_Map_keys_u32_values_crate_nested_HashID_FFI_values_get(long jarg1, std_collections_Map_keys_u32_values_crate_nested_HashID_FFI jarg1_);
  public final static native long new_std_collections_Map_keys_u32_values_crate_nested_HashID_FFI();
  public final static native void delete_std_collections_Map_keys_u32_values_crate_nested_HashID_FFI(long jarg1);
  public final static native void std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI_count_set(long jarg1, std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI jarg1_, long jarg2);
  public final static native long std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI_count_get(long jarg1, std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI jarg1_);
  public final static native void std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI_keys_set(long jarg1, std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI jarg1_, long jarg2);
  public final static native long std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI_keys_get(long jarg1, std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI jarg1_);
  public final static native void std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI_values_set(long jarg1, std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI jarg1_, long jarg2);
  public final static native long std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI_values_get(long jarg1, std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI jarg1_);
  public final static native long new_std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI();
  public final static native void delete_std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI(long jarg1);
  public final static native long ffiGetAnIdentity();
  public final static native long ffiFindHashByU32(long jarg1, long jarg2, std_collections_Map_keys_u32_values_crate_nested_HashID_FFI jarg2_);
  public final static native long ffiGetIdentity(long jarg1, Identifier jarg1_);
  public final static native String ffiGetChainHashesByMap(long jarg1, std_collections_Map_keys_crate_chain_common_chain_type_ChainType_values_crate_nested_HashID_FFI jarg1_);
  public final static native String ffiAddressWithScriptPubkey(byte[] jarg1);
  public final static native String ffiGetChainTypeString(long jarg1);
}