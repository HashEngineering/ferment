
%extend ContractBounds {
//     static ContractBounds * singleContract(Identifier * id) {
//         Identifier * idCopy = Identifier_clone(id);
//         return ContractBounds_SingleContract_ctor(idCopy);
//     }
//     static ContractBounds * singleContract2(Identifier * id) {
//         return ContractBounds_SingleContract_ctor(id);
//     }
//     static ContractBounds * singleContractDocumentType(Identifier * id, char * type) {
//         Identifier * idCopy = Identifier_clone(id);
//         char * typeCopy = memoryFactory.clone(type);
//         // printf("type %s, typeCopy %s", type, typeCopy);
//         return ContractBounds_SingleContractDocumentType_ctor(idCopy, type);
//     }
    ContractBounds(Identifier * id) {
        return ContractBounds_SingleContract_ctor(Identifier_clone(id));
    }
    ContractBounds(Identifier * id, char * document_type) {
        return ContractBounds_SingleContractDocumentType_ctor(Identifier_clone(id), memoryFactory.clone(document_type));
    }
    ~ContractBounds() {
        ContractBounds_destroy($self);
    }
};
