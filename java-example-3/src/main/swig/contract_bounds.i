
%extend ContractBounds {
    static ContractBounds * singleContract(Identifier * id) {
        return ContractBounds_SingleContract_ctor(id);
    }
    static ContractBounds * singleContract(Identifier * id, char * type) {
        return ContractBounds_SingleContractDocumentType_ctor(id, type);
    }
    ~ContractBounds() {
        ContractBounds_destroy($self);
    }
};
