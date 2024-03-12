%ignore crate_identity_identity_Revision::_0;
%rename(Revision) crate_identity_identity_Revision;
%extend crate_identity_identity_Revision {
    //
    crate_identity_identity_Revision() {
        return crate_identity_identity_Revision_ctor(0);
    }
    crate_identity_identity_Revision(long long timestamp) {
        return crate_identity_identity_Revision_ctor(timestamp);
    }
    ~crate_identity_identity_Revision() {
        crate_identity_identity_Revision_destroy($self);
    }

    long long toLong() {
        return $self->_0;
    }

    bool objectEquals(crate_identity_identity_Revision* other) {
        if ($self == other) return true;
        if ($self == nullptr || other == nullptr) return false;
        return $self->_0 == other->_0;
    }
}