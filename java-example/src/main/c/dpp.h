#ifndef DPP_H
#define DPP_H


class IHaveChainSettings {
private:
    IHaveChainSettings_TraitObject m_traitObject;
public:
    IHaveChainSettings(const IHaveChainSettings_TraitObject &traitObject)
        : m_traitObject(traitObject) {}

   char *name() const {
        return m_traitObject.vtable->name(m_traitObject.object);
    }

    HashID_FFI *genesis_hash() const {
        return m_traitObject.vtable->genesis_hash(m_traitObject.object);
    }

    uint32_t genesis_height() const {
        return m_traitObject.vtable->genesis_height(m_traitObject.object);
    }

    bool has_genesis_hash(HashID_FFI *hash) const {
        return m_traitObject.vtable->has_genesis_hash(m_traitObject.object, hash);
    }

    HashID_FFI *get_hash_by_hash(HashID_FFI *hash) const {
        return m_traitObject.vtable->get_hash_by_hash(m_traitObject.object, hash);
    }

    bool should_process_llmq_of_type(uint16_t llmq_type) const {
        return m_traitObject.vtable->should_process_llmq_of_type(m_traitObject.object, llmq_type);
    }
};

class IdentityFactory {
    IdentityFactory_TraitObject * myFactory;
public:
    IdentityFactory(IdentityFactory_TraitObject * myFactory) {
        this->myFactory = myFactory;
    }

    Identity_FFI getIdentity(Identifier_FFI * id) {
        return myFactory.vtable->getIdentity(id);
    }
};

#endif // this file