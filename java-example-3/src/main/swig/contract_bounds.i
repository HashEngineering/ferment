%rename (ContractBounds) ferment_example_identity_identity_ContractBounds;
%rename (ContractBounds_Tag) ferment_example_identity_identity_ContractBounds_Tag;
%rename (SingleContract) ferment_example_identity_identity_ContractBounds_SingleContract;
%rename (SingleContractDocumentType) ferment_example_identity_identity_ContractBounds_SingleContractDocumentType;

%extend ferment_example_identity_identity_ContractBounds {
    ferment_example_identity_identity_ContractBounds(ferment_example_nested_Identifier * id) {
        ferment_example_identity_identity_ContractBounds * cb = ferment_example_identity_identity_ContractBounds_SingleContract_ctor(Identifier_clone(id));
        printf("ContractBounds: %lx->%lx\n", (unsigned long)cb, cb->single_contract.id);
        return cb;
    }
    ferment_example_identity_identity_ContractBounds(ferment_example_nested_Identifier * id, char * document_type) {
        return ferment_example_identity_identity_ContractBounds_SingleContractDocumentType_ctor(Identifier_clone(id), memoryFactory.clone(document_type));
    }
    ~ferment_example_identity_identity_ContractBounds() {
        printf("~ContractBounds: %lx->%lx\n", (unsigned long)$self, $self->single_contract.id);
        ferment_example_identity_identity_ContractBounds_destroy($self);
    }
};

%newobject singleContract(ferment_example_nested_Identifier *);
%newobject singleContractDocumentType(ferment_example_nested_Identifier *, char *);
%ignore ferment_example_identity_identity_ContractBounds_SingleContract_ctor(struct ferment_example_nested_Identifier *id);
%ignore ferment_example_identity_identity_ContractBounds_SingleContractDocumentType_ctor(struct ferment_example_nested_Identifier *id, char *document_type_name);
