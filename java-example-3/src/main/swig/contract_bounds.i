%rename (ContractBounds) crate_identity_identity_ContractBounds;
%rename (ContractBounds_Tag) crate_identity_identity_ContractBounds_Tag;
%rename (SingleContract) crate_identity_identity_ContractBounds_SingleContract;
%rename (SingleContractDocumentType) crate_identity_identity_ContractBounds_SingleContractDocumentType;

%extend crate_identity_identity_ContractBounds {
    crate_identity_identity_ContractBounds(crate_nested_Identifier * id) {
        crate_identity_identity_ContractBounds * cb = crate_identity_identity_ContractBounds_SingleContract_ctor(Identifier_clone(id));
        printf("ContractBounds: %lx->%lx\n", (unsigned long)cb, cb->single_contract.id);
        return cb;
    }
    crate_identity_identity_ContractBounds(crate_nested_Identifier * id, char * document_type) {
        return crate_identity_identity_ContractBounds_SingleContractDocumentType_ctor(Identifier_clone(id), memoryFactory.clone(document_type));
    }
    ~crate_identity_identity_ContractBounds() {
        printf("~ContractBounds: %lx->%lx\n", (unsigned long)$self, $self->single_contract.id);
        crate_identity_identity_ContractBounds_destroy($self);
    }
};

%newobject singleContract(crate_nested_Identifier *);
%newobject singleContractDocumentType(crate_nested_Identifier *, char *);
%ignore crate_identity_identity_ContractBounds_SingleContract_ctor(struct crate_nested_Identifier *id);
%ignore crate_identity_identity_ContractBounds_SingleContractDocumentType_ctor(struct crate_nested_Identifier *id, char *document_type_name);
