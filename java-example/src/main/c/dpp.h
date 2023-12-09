#ifndef DPP_H
#define DPP_H

class IHaveChainSettings {
private:
    const IHaveChainSettings_TraitObject * m_traitObject;
public:
    IHaveChainSettings(const IHaveChainSettings_TraitObject * traitObject)
        : m_traitObject(traitObject) {}

   char *name() const {
        return m_traitObject->vtable->name(m_traitObject->object);
    }

    HashID *genesis_hash() const {
        return m_traitObject->vtable->genesis_hash(m_traitObject->object);
    }

    uint32_t genesis_height() const {
        return m_traitObject->vtable->genesis_height(m_traitObject->object);
    }

    bool has_genesis_hash(HashID *hash) const {
        return m_traitObject->vtable->has_genesis_hash(m_traitObject->object, hash);
    }

    HashID *get_hash_by_hash(HashID *hash) const {
        return m_traitObject->vtable->get_hash_by_hash(m_traitObject->object, hash);
    }

    bool should_process_llmq_of_type(uint16_t llmq_type) const {
        return m_traitObject->vtable->should_process_llmq_of_type(m_traitObject->object, llmq_type);
    }
};

class IdentityFactory {
    IdentityFactory_TraitObject * myFactory;
public:
    IdentityFactory(IdentityFactory_TraitObject * myFactory) {
        this->myFactory = myFactory;
    }

    Identity * getIdentity(Identifier * id) {
        return myFactory->vtable->get_identity(myFactory, id);
    }
};

extern "C" Vec_u8 * Vec_u8_ctor(uint32_t count, uint8_t * values) {
    Vec_u8 * result = new Vec_u8;
    result->count = count;
    result->values = new uint8_t[count];
    memcpy(result->values, values, count);
    return result;
}


#endif // this file