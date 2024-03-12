%ignore KeyID::_0;
%rename(KeyID) crate_identity_identity_KeyID;
%extend crate_identity_identity_KeyID {
    crate_identity_identity_KeyID(int id) {
        return crate_identity_identity_KeyID_ctor(id);
    }
    ~crate_identity_identity_KeyID() {
        crate_identity_identity_KeyID_destroy($self);
    }
    int toInt() {
        return $self->_0;
    }
    bool objectEquals(crate_identity_identity_KeyID* other) {
        if ($self == other) return true;
        if ($self == nullptr || other == nullptr) return false;
        return $self->_0 == other->_0;
    }
}