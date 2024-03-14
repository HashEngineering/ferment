#ifndef DPP_H
#define DPP_H

#include <list>
#include <map>
#include <algorithm>
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

    char * clone(char * str) {
        if (str != nullptr) {
            int len = strlen(str) + 1;
            char * strClone = reinterpret_cast<char*>(alloc(len));
            strcpy(strClone, str);
            return strClone;
        }
        return nullptr;
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

extern MemoryFactory & memoryFactory;

#define ENUM_CASE(enum_class, value) \
    case ferment_example_identity_identity_##enum_class##_##value: \
        return ferment_example_identity_identity_##enum_class##_##value##_ctor();

ferment_example_identity_identity_KeyType * intToKeyType(int value) {
    switch(value) {
        ENUM_CASE(KeyType, ECDSA_SECP256K1)
        ENUM_CASE(KeyType, BLS12_381)
        ENUM_CASE(KeyType, ECDSA_HASH160)
        ENUM_CASE(KeyType, BIP13_SCRIPT_HASH)
        ENUM_CASE(KeyType, EDDSA_25519_HASH160)
    }
}

ferment_example_identity_identity_SecurityLevel * intToSecurityLevel(int value) {
    switch(value) {
        ENUM_CASE(SecurityLevel, MASTER)
        ENUM_CASE(SecurityLevel, CRITICAL)
        ENUM_CASE(SecurityLevel, HIGH)
        ENUM_CASE(SecurityLevel, MEDIUM)
    }
}
ferment_example_identity_identity_Purpose * intToPurpose(int value) {
     switch(value) {
        ENUM_CASE(Purpose, AUTHENTICATION)
        ENUM_CASE(Purpose, DECRYPTION)
        ENUM_CASE(Purpose, ENCRYPTION)
        ENUM_CASE(Purpose, WITHDRAW)
        ENUM_CASE(Purpose, SYSTEM)
        ENUM_CASE(Purpose, VOTING)
     }
}

ferment_example_nested_Identifier * Identifier_clone(ferment_example_nested_Identifier * id) {
     uint8_t * bytesCopy = (uint8_t*)memoryFactory.alloc(32);
     memcpy(bytesCopy, id->_0->_0, 32);
     return ferment_example_nested_Identifier_ctor(ferment_example_nested_IdentifierBytes32_ctor((uint8_t (*)[32])bytesCopy));
}

ferment_example_identity_identity_ContractBounds * singleContract(ferment_example_nested_Identifier * id) {
        ferment_example_nested_Identifier * idCopy = Identifier_clone(id);
        ferment_example_identity_identity_ContractBounds * cb = ferment_example_identity_identity_ContractBounds_SingleContract_ctor(idCopy);
        return cb;
}

static ferment_example_identity_identity_ContractBounds * singleContractDocument(ferment_example_nested_Identifier * id, char * type) {
    ferment_example_nested_Identifier * idCopy = Identifier_clone(id);
    char * typeCopy = memoryFactory.clone(type);
    return ferment_example_identity_identity_ContractBounds_SingleContractDocumentType_ctor(idCopy, typeCopy);
}

#endif // this file