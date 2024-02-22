%newobject ContractBounds_SingleContract_ctor;
%newobject ContractBounds_SingleContractDocumentType_ctor;
%extend ContractBounds {
//     static ContractBounds * singleContract(Identifier * id) {
//         Identifier * idCopy = Identifier_clone(id);
//         ContractBounds * cb = ContractBounds_SingleContract_ctor(idCopy);
//         printf("singleContract: %lx->%lx\n", (unsigned long)cb, cb->single_contract.id);
//         return cb;
//     }
//
//     static ContractBounds * singleContractDocumentType(Identifier * id, char * type) {
//         Identifier * idCopy = Identifier_clone(id);
//         char * typeCopy = memoryFactory.clone(type);
//         // printf("type %s, typeCopy %s", type, typeCopy);
//         return ContractBounds_SingleContractDocumentType_ctor(idCopy, type);
//     }
    ContractBounds(Identifier * id) {
        ContractBounds * cb = ContractBounds_SingleContract_ctor(Identifier_clone(id));
        printf("ContractBounds: %lx->%lx\n", (unsigned long)cb, cb->single_contract.id);
        return cb;
    }
    ContractBounds(Identifier * id, char * document_type) {
        return ContractBounds_SingleContractDocumentType_ctor(Identifier_clone(id), memoryFactory.clone(document_type));
    }
    ~ContractBounds() {
        printf("~ContractBounds: %lx->%lx\n", (unsigned long)$self, $self->single_contract.id);
        ContractBounds_destroy($self);
    }
    bool isNull() {
        return (uint64_t)$self < 10;
    }
};


%newobject singleContract(Identifier *);
%newobject singleContractDocumentType(Identifier *, char *);
%ignore ContractBounds_SingleContract_ctor(struct Identifier *id);
%ignore ContractBounds_SingleContractDocumentType_ctor(struct Identifier *id, char *document_type_name);

// class SingleContract : public ContractBounds {
//     SingleContract(Identifier * id) {
//
//     }
// }