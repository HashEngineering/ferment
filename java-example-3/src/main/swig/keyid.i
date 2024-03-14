%ignore KeyID::_0;
%rename(KeyID) ferment_example_identity_identity_KeyID;
%extend ferment_example_identity_identity_KeyID {
    ferment_example_identity_identity_KeyID(int id) {
        return ferment_example_identity_identity_KeyID_ctor(id);
    }
    ~ferment_example_identity_identity_KeyID() {
        ferment_example_identity_identity_KeyID_destroy($self);
    }
    int toInt() {
        return $self->_0;
    }
    bool objectEquals(ferment_example_identity_identity_KeyID* other) {
        if ($self == other) return true;
        if ($self == nullptr || other == nullptr) return false;
        return $self->_0 == other->_0;
    }
}