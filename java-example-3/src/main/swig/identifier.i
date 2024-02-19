%extend Identifier {
    Identifier(uint8_t (*byteArray)[32]) {
        IdentifierBytes32 * identifierBytes32 = IdentifierBytes32_ctor(byteArray);
        return Identifier_ctor(identifierBytes32);
    }

    ~Identifier() {
        printf("~Identifier(%lx)\n", $self);
        //memoryFactory.destroyItem($self->_0->_0); //crash
        Identifier_destroy($self);
    }
}

%extend IdentifierBytes32 {
    IdentifierBytes32(uint8_t (*identifierBytes)[32]) {
        return IdentifierBytes32_ctor(identifierBytes);
    }

    ~IdentifierBytes32() {
        printf("~IdentityBytes32(%lx)\n", (uint64_t)$self);
        memoryFactory.destroyItem($self->_0); // crash
        IdentifierBytes32_destroy($self);
    }
};