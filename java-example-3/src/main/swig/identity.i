%ignore IdentityV0::public_keys;
%ignore IdentityV0::balance;
%extend IdentityV0 {
    ~IdentityV0() {
        printf("~IdentityV0(%lx)\n", (uint64_t)$self);
        IdentityV0_destroy($self);
    }

    int getPublicKeyCount() {
        return $self->public_keys->count;
    }

    struct IdentityPublicKeyV0 * getPublicKey(uint32_t index) {
        if (index < $self->public_keys->count) {
            return $self->public_keys->values[index]->v0;
        } else {
            return NULL;
        }
    }

    struct IdentityPublicKeyV0 * getPublicKeyById(uint32_t id) {
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

%extend Identity {
    Identity() {
        return get_an_identity();
    }
    ~Identity() {
        printf("~Identity(%lx)\n", (uint64_t)$self);
        Identity_destroy($self);
    }
}

%newobject get_identity2(struct Identifier *);
%newobject get_an_identity(void);
%newobject create_basic_identity_v0(uint8_t (*)[32]);
%newobject get_identity_contract_bounds(struct Identifier *identifier, struct Identifier *contract_identifier);