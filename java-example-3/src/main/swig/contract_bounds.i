
%extend ContractBounds {
    static ContractBounds * singleContract(Identifier * id) {
        uint8_t * bytesCopy = (uint8_t*)memoryFactory.alloc(32);
        memcpy(bytesCopy, id->_0->_0, 32);
        Identifier * idCopy = Identifier_ctor(IdentifierBytes32_ctor((uint8_t (*)[32])bytesCopy));
        // Identifier * idCopy = Identifier_ctor(IdentifierBytes32_ctor(id->_0->_0));
        return ContractBounds_SingleContract_ctor(idCopy);
    }
    static ContractBounds * singleContractDocumentType(Identifier * id, char * type) {
        uint8_t * bytesCopy = (uint8_t*)memoryFactory.alloc(32);
        memcpy(bytesCopy, id->_0->_0, 32);
        Identifier * idCopy = Identifier_ctor(IdentifierBytes32_ctor((uint8_t (*)[32])bytesCopy));
        return ContractBounds_SingleContractDocumentType_ctor(idCopy, type);
    }
    ~ContractBounds() {
        ContractBounds_destroy($self);
    }
};
