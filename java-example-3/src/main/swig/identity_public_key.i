%ignore IdentityPublicKeyV0::key_type;
%ignore IdentityPublicKeyV0::purpose;
%ignore IdentityPublicKeyV0::security_level;

%extend IdentityPublicKeyV0 {
    IdentityPublicKeyV0(KeyID * keyId, Purpose purpose, SecurityLevel securityLevel,
        ContractBounds contract_bounds,
        KeyType key_type, bool read_only, BinaryData * data,
        TimestampMillis * disabled_at) {
        printf("new_IdentityPublicKeyV0 purpose %d\n", purpose);
         Purpose * purposeObject;
         switch(purpose) {
             case Purpose_AUTHENTICATION:
                 purposeObject = Purpose_AUTHENTICATION_ctor();
                 break;
             case Purpose_DECRYPTION:
                 purposeObject = Purpose_DECRYPTION_ctor();
                 break;
             case Purpose_ENCRYPTION:
                purposeObject = Purpose_ENCRYPTION_ctor();
                break;
            case Purpose_WITHDRAW:
                purposeObject = Purpose_WITHDRAW_ctor();
                break;
            case Purpose_SYSTEM:
                purposeObject = Purpose_SYSTEM_ctor();
                break;
            case Purpose_VOTING:
                purposeObject = Purpose_VOTING_ctor();
                break;
         }
        printf("  %lx\n", (uint64_t)purposeObject);
        KeyType * keyTypeObject = intToKeyType(key_type);
        SecurityLevel * securityLevelObject = intToSecurityLevel(securityLevel);
        uint8_t * byteArray = (uint8_t*)memoryFactory.alloc(data->_0->count);
        memcpy(byteArray, data->_0->values, data->_0->count);
        Vec_u8 * vec_u8 = Vec_u8_ctor(byteArray, data->_0->count);
        BinaryData * binaryData = BinaryData_ctor(vec_u8);
        printf("  ->data(%lx)\n", (uint64_t)binaryData);
        printf("  ->data->_0(%lx)\n", (uint64_t)binaryData->_0);
        printf("  ->data->_0->values(%lx)\n", (uint64_t)binaryData->_0->values);
        ContractBounds * contract_bounds_copy = nullptr;
        KeyID * keyIdObject = KeyID_ctor(keyId->_0);
        IdentityPublicKeyV0 * ipkv0 = IdentityPublicKeyV0_ctor(keyIdObject, purposeObject, securityLevelObject,
            contract_bounds_copy,
            keyTypeObject, read_only, binaryData, disabled_at);
        printf("IdentityPublicKeyV0(%lx\n", (uint64_t)ipkv0);
        return ipkv0;
    }
    ~IdentityPublicKeyV0() {
        printf("~IdentityPublicKeyV0(%lx)\n", (unsigned long)$self);
        printf("  ->purpose(%lx)\n", (uint64_t)$self->purpose);
        //Purpose_destroy($self->purpose);
        //SecurityLevel_destroy($self->security_level);
        //KeyType_destroy($self->key_type);
        //KeyID_destroy($self->id);
        printf("  ->data->_0(%lx)\n", (uint64_t)$self->data->_0);
        printf("  ->data->_0->values(%lx)\n", (uint64_t)$self->data->_0->values);
        //memoryFactory.destroyItem($self->data->_0->values);
        // Vec_u8_destroy($self->data->_0); // crash
        printf("  ->data(%lx)\n", (uint64_t)$self->data);
        // BinaryData_destroy($self->data); // crash
        //TimestampMillis_destroy($self->disabled_at);
        IdentityPublicKeyV0_destroy($self); //crash
    }
    enum KeyType getKeyType() {
        return *$self->key_type;
    }
    enum Purpose getPurpose() {
        return *$self->purpose;
    }
    enum Purpose * getPurpose2() {
        return $self->purpose;
    }
    enum SecurityLevel getSecurityLevel() {
        return *self->security_level;
    }
}