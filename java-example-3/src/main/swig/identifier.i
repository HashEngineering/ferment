%rename(Identifier) ferment_example_nested_Identifier;
%extend ferment_example_nested_Identifier {
    ferment_example_nested_Identifier(uint8_t (*byteArray)[32]) {
        ferment_example_nested_IdentifierBytes32 * identifierBytes32 = ferment_example_nested_IdentifierBytes32_ctor(byteArray);
        return ferment_example_nested_Identifier_ctor(identifierBytes32);
    }

    ~ferment_example_nested_Identifier() {
        printf("~Identifier(%lx)\n", $self);
        //memoryFactory.destroyItem($self->_0->_0); //crash
        ferment_example_nested_Identifier_destroy($self);
    }
}

%rename (IdentifierBytes32) ferment_example_nested_IdentifierBytes32;
%extend ferment_example_nested_IdentifierBytes32 {
    ferment_example_nested_IdentifierBytes32(uint8_t (*identifierBytes)[32]) {
        return ferment_example_nested_IdentifierBytes32_ctor(identifierBytes);
    }

    ~ferment_example_nested_IdentifierBytes32() {
        printf("~IdentityBytes32(%lx)\n", (uint64_t)$self);
        // memoryFactory.destroyItem($self->_0); // crash
        ferment_example_nested_IdentifierBytes32_destroy($self);
    }
};