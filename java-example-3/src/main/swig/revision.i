%ignore Revision::_0;
%extend Revision {
    //
    Revision() {
        return Revision_ctor(0);
    }
    Revision(long long timestamp) {
        return Revision_ctor(timestamp);
    }
    ~Revision() {
        Revision_destroy($self);
    }

    long long toLong() {
        return $self->_0;
    }
}