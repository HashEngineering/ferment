%ignore ferment_example_identity_identity_IdentityPublicKeyV0::key_type;
%ignore ferment_example_identity_identity_IdentityPublicKeyV0::purpose;
%ignore ferment_example_identity_identity_IdentityPublicKeyV0::security_level;
%rename(IdentityPublicKeyV0) ferment_example_identity_identity_IdentityPublicKeyV0;
%extend ferment_example_identity_identity_IdentityPublicKeyV0 {
    ferment_example_identity_identity_IdentityPublicKeyV0(ferment_example_identity_identity_KeyID * keyId,
        ferment_example_identity_identity_Purpose purpose,
        ferment_example_identity_identity_SecurityLevel securityLevel,
        ferment_example_identity_identity_ContractBounds * contract_bounds,
        ferment_example_identity_identity_KeyType key_type, bool read_only, ferment_example_nested_BinaryData * data,
        ferment_example_identity_identity_TimestampMillis * disabled_at) {

        // enums
        ferment_example_identity_identity_Purpose * purposeObject = intToPurpose(purpose);
        ferment_example_identity_identity_KeyType * keyTypeObject = intToKeyType(key_type);
        ferment_example_identity_identity_SecurityLevel * securityLevelObject = intToSecurityLevel(securityLevel);

        uint8_t * byteArray = (uint8_t*)memoryFactory.alloc(data->_0->count);
        memcpy(byteArray, data->_0->values, data->_0->count);
        Vec_u8 * vec_u8 = Vec_u8_ctor(data->_0->count, byteArray);
        ferment_example_nested_BinaryData * binaryData = ferment_example_nested_BinaryData_ctor(vec_u8);
        printf("  ->data(%lx)\n", (uint64_t)binaryData);
        printf("  ->data->_0(%lx)\n", (uint64_t)binaryData->_0);
        printf("  ->data->_0->values(%lx)\n", (uint64_t)binaryData->_0->values);
        ferment_example_identity_identity_ContractBounds * contract_bounds_copy = nullptr;
        if (contract_bounds != nullptr) {
            if (contract_bounds->tag == ferment_example_identity_identity_ContractBounds_SingleContract) {
                contract_bounds_copy = ferment_example_identity_identity_ContractBounds_SingleContract_ctor(Identifier_clone(contract_bounds->single_contract.id));
            } else if (contract_bounds->tag == ferment_example_identity_identity_ContractBounds_SingleContractDocumentType) {
                char * typeCopy = memoryFactory.clone(contract_bounds->single_contract_document_type.document_type_name);
                contract_bounds_copy = ferment_example_identity_identity_ContractBounds_SingleContractDocumentType_ctor(Identifier_clone(contract_bounds->single_contract_document_type.id), typeCopy);
            }
        }
        printf("  ->contract_bounds(%lx): %d\n", (uint64_t)contract_bounds, contract_bounds != nullptr ? contract_bounds->tag : -1);
        printf("  ->contract_bounds_copy(%lx)\n", (uint64_t)contract_bounds_copy);
        ferment_example_identity_identity_KeyID * keyIdObject = ferment_example_identity_identity_KeyID_ctor(keyId->_0);
        ferment_example_identity_identity_TimestampMillis * disabled_at_copy = disabled_at != nullptr ? ferment_example_identity_identity_TimestampMillis_ctor(disabled_at->_0) : nullptr;
        ferment_example_identity_identity_IdentityPublicKeyV0 * ipkv0 = ferment_example_identity_identity_IdentityPublicKeyV0_ctor(keyIdObject, purposeObject, securityLevelObject,
            contract_bounds_copy,
            keyTypeObject, read_only, binaryData, disabled_at_copy);
        printf("IdentityPublicKeyV0(%lx\n", (uint64_t)ipkv0);
        return ipkv0;
    }
    ~ferment_example_identity_identity_IdentityPublicKeyV0() {
//         printf("~IdentityPublicKeyV0(%lx)\n", (unsigned long)$self);
//         printf("  ->purpose(%lx)\n", (uint64_t)$self->purpose);
//         printf("  ->data->_0(%lx)\n", (uint64_t)$self->data->_0);
//         printf("  ->data->_0->values(%lx)\n", (uint64_t)$self->data->_0->values);
//         printf("  ->data(%lx)\n", (uint64_t)$self->data);
        ferment_example_identity_identity_IdentityPublicKeyV0_destroy($self); //crash
    }
    enum ferment_example_identity_identity_KeyType getKeyType() {
        return *$self->key_type;
    }
    enum ferment_example_identity_identity_Purpose getPurpose() {
        return *$self->purpose;
    }
    enum ferment_example_identity_identity_SecurityLevel getSecurityLevel() {
        return *self->security_level;
    }
}

%rename(IdentityPublicKey) ferment_example_identity_identity_IdentityPublicKey;
%rename(IdentityPublicKey_Tag) ferment_example_identity_identity_IdentityPublicKey_Tag;

%newobject random_key;
%newobject random_key_args;