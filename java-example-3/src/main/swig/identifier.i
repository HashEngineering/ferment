%rename(Identifier) crate_nested_Identifier;
%extend crate_nested_Identifier {
    crate_nested_Identifier(uint8_t (*byteArray)[32]) {
        crate_nested_IdentifierBytes32 * identifierBytes32 = crate_nested_IdentifierBytes32_ctor(byteArray);
        return crate_nested_Identifier_ctor(identifierBytes32);
    }

    ~crate_nested_Identifier() {
        printf("~Identifier(%lx)\n", $self);
        //memoryFactory.destroyItem($self->_0->_0); //crash
        crate_nested_Identifier_destroy($self);
    }
}

%rename (IdentifierBytes32) crate_nested_IdentifierBytes32;
%extend crate_nested_IdentifierBytes32 {
    crate_nested_IdentifierBytes32(uint8_t (*identifierBytes)[32]) {
        return crate_nested_IdentifierBytes32_ctor(identifierBytes);
    }

    ~crate_nested_IdentifierBytes32() {
        printf("~IdentityBytes32(%lx)\n", (uint64_t)$self);
        // memoryFactory.destroyItem($self->_0); // crash
        crate_nested_IdentifierBytes32_destroy($self);
    }
};