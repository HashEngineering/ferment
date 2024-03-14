%ignore ferment_example_identity_identity_Revision::_0;
%rename(Revision) ferment_example_identity_identity_Revision;
%extend ferment_example_identity_identity_Revision {
    //
    ferment_example_identity_identity_Revision() {
        return ferment_example_identity_identity_Revision_ctor(0);
    }
    ferment_example_identity_identity_Revision(long long timestamp) {
        return ferment_example_identity_identity_Revision_ctor(timestamp);
    }
    ~ferment_example_identity_identity_Revision() {
        ferment_example_identity_identity_Revision_destroy($self);
    }

    long long toLong() {
        return $self->_0;
    }

    bool objectEquals(ferment_example_identity_identity_Revision* other) {
        if ($self == other) return true;
        if ($self == nullptr || other == nullptr) return false;
        return $self->_0 == other->_0;
    }
}