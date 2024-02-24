%extend BinaryData {
    BinaryData(Vec_u8 *o_0) {
        return BinaryData_ctor(o_0);
    }
    ~BinaryData() {
        printf("~BinaryData(%lx)\n", (uint64_t)$self);
        printf("~BinaryData->_0(%lx)\n", (uint64_t)$self->_0);

        uint8_t * ptr = $self->_0->values;
        // memoryFactory.destroyItem($self->_0->values); // causes BinaryData_destroy crash
        printf("~BinaryData->_0->values(%lx), [0] = %d\n", (uint64_t)$self->_0->values, (int)ptr[0]);

        // Vec_u8_destroy($self->_0); // crash
        printf("~BinaryData->_0(%lx)\n", (uint64_t)$self->_0);
        BinaryData_destroy($self);
        //printf("~BinaryData complete(%lx)\n", (uint64_t)$self);
        //printf("~BinaryData->_0->values[0] = %d\n", (int)ptr[0]);

        //memoryFactory.destroyItem(ptr);
    }
}