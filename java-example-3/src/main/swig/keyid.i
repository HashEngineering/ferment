%ignore KeyID::_0;
%extend KeyID {
    KeyID(int id) {
        return KeyID_ctor(id);
    }
    ~KeyID() {
        KeyID_destroy($self);
    }
    int toInt() {
        return $self->_0;
    }
    bool objectEquals(KeyID* other) {
        if ($self == other) return true;
        if ($self == nullptr || other == nullptr) return false;
        return $self->_0 == other->_0;
    }
}