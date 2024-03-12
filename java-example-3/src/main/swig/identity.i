%ignore crate_identity_identity_IdentityV0::public_keys;
%ignore crate_identity_identity_IdentityV0::balance;
%rename(IdentityV0) crate_identity_identity_IdentityV0;
%extend crate_identity_identity_IdentityV0 {
    ~crate_identity_identity_IdentityV0() {
        printf("~IdentityV0(%lx)\n", (uint64_t)$self);
        crate_identity_identity_IdentityV0_destroy($self);
    }

    int getPublicKeyCount() {
        return $self->public_keys->count;
    }

    struct crate_identity_identity_IdentityPublicKeyV0 * getPublicKey(uint32_t index) {
        if (index < $self->public_keys->count) {
            return $self->public_keys->values[index]->v0;
        } else {
            return NULL;
        }
    }

    struct crate_identity_identity_IdentityPublicKeyV0 * getPublicKeyById(uint32_t id) {
        for (int i = 0; i < $self->public_keys->count; ++i) {
            if ($self->public_keys->keys[i]->_0 == id)
                return $self->public_keys->values[i]->v0;
        }
        return NULL;
    }

    long long getBalance() {
        return (long)$self->balance;
    }
}

%extend crate_identity_identity_Identity {
    crate_identity_identity_Identity() {
        return get_an_identity();
    }
    ~crate_identity_identity_Identity() {
        printf("~Identity(%lx)\n", (uint64_t)$self);
        crate_identity_identity_Identity_destroy($self);
    }
}
%rename(Identity) crate_identity_identity_Identity;
%rename(Identity_Tag) crate_identity_identity_Identity_Tag;
%rename(IdentityV0Type) crate_identity_identity_Identity_V0;

%newobject get_identity2(struct crate_nested_Identifier *);
%newobject get_an_identity(void);
%newobject create_basic_identity_v0(uint8_t (*)[32]);
%newobject get_identity_contract_bounds(struct crate_nested_Identifier *identifier, struct crate_nested_Identifier *contract_identifier);