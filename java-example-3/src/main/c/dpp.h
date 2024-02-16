#ifndef DPP_H
#define DPP_H

#include <list>
#include <map>
using namespace std;

//class IHaveChainSettings {
//private:
//    const IHaveChainSettings_TraitObject * m_traitObject;
//public:
//    IHaveChainSettings(const IHaveChainSettings_TraitObject * traitObject)
//        : m_traitObject(traitObject) {}
//
//   char *name() const {
//        return m_traitObject->vtable->name(m_traitObject->object);
//    }
//
//    HashID *genesis_hash() const {
//        return m_traitObject->vtable->genesis_hash(m_traitObject->object);
//    }
//
//    uint32_t genesis_height() const {
//        return m_traitObject->vtable->genesis_height(m_traitObject->object);
//    }
//
//    bool has_genesis_hash(HashID *hash) const {
//        return m_traitObject->vtable->has_genesis_hash(m_traitObject->object, hash);
//    }
//
//    HashID *get_hash_by_hash(HashID *hash) const {
//        return m_traitObject->vtable->get_hash_by_hash(m_traitObject->object, hash);
//    }
//
//    bool should_process_llmq_of_type(uint16_t llmq_type) const {
//        return m_traitObject->vtable->should_process_llmq_of_type(m_traitObject->object, llmq_type);
//    }
//};

//class IdentityFactory {
//    IdentityFactory_TraitObject * myFactory;
//public:
//    IdentityFactory(IdentityFactory_TraitObject * myFactory) {
//        this->myFactory = myFactory;
//    }
//
//    Identity * getIdentity(Identifier * id) {
//        return myFactory->vtable->get_identity(myFactory, id);
//    }
//};

class MemoryFactory {
    map<long, list<uint8_t*>> memoryMap;
    list<uint8_t*> memoryList;
    static MemoryFactory * instance;
public:
    static MemoryFactory * getInstance() { return instance; }
    MemoryFactory() {

    }

    ~MemoryFactory() {
        for (pair<long, list<uint8_t*>> pointerList: memoryMap) {
            for (uint8_t * item : pointerList.second) {
                delete [] item;
            }
        }
        memoryMap.clear();
        for (uint8_t * item : memoryList) {
            delete [] item;
        }
        memoryList.clear();
    }

    size_t size() {
        return memoryList.size();
    }

    void * alloc(size_t size) {
        uint8_t * memory = new uint8_t[size];
        printf("  adding %lx with size %ld\n", (unsigned long)memory, size);
        memoryList.push_back(memory);
        return reinterpret_cast<void*>(memory);
    }

    void * alloc(void * root, size_t size) {
        uint8_t * memory = new uint8_t[size];
        if (memoryMap.count((long)root)) {
            memoryMap[(long)root].push_back(memory);
        } else {
            list<uint8_t*> list;
            list.push_back(memory);
            memoryMap.insert(pair<long, ::list<uint8_t*>>((long)root, list));
        }
        return reinterpret_cast<void*>(memory);
    }

    void destroy(void * root, void * memory) {
        if (memoryMap.count((long)root)) {
            for (uint8_t * item: memoryMap[(long)root]) {
                if (item == memory)
                    delete [] item;
            }
        }
    }

    void destroy(void * root) {
        if (memoryMap.count((long)root)) {
            for (uint8_t * item: memoryMap[(long)root]) {
                delete [] item;
            }
            memoryMap.erase((long)root);
        }
    }

    void destroyItem(void * item) {
    list<uint8_t*>::iterator it = find(memoryList.begin(), memoryList.end(), item);
        if (it != memoryList.end()) {
            printf("destroying item [%lx]\n", (unsigned long)item);
            delete [] (uint8_t*)item;
            memoryList.erase(it);
            printf("item destroyed [%lx]\n", (unsigned long)item);
        } else printf("not destroying item %lx\n", (unsigned long)item);
    }
};

#define ENUM_CASE(enum_class, value) \
    case enum_class##_##value: \
        return enum_class##_##value##_ctor();

KeyType * intToKeyType(int value) {
    switch(value) {
        ENUM_CASE(KeyType, ECDSA_SECP256K1)
        ENUM_CASE(KeyType, BLS12_381)
        ENUM_CASE(KeyType, ECDSA_HASH160)
        ENUM_CASE(KeyType, BIP13_SCRIPT_HASH)
        ENUM_CASE(KeyType, EDDSA_25519_HASH160)
    }
}

SecurityLevel * intToSecurityLevel(int value) {
    switch(value) {
        ENUM_CASE(SecurityLevel, MASTER)
        ENUM_CASE(SecurityLevel, CRITICAL)
        ENUM_CASE(SecurityLevel, HIGH)
        ENUM_CASE(SecurityLevel, MEDIUM)
    }
}



#endif // this file