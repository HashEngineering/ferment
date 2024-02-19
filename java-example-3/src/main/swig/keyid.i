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
}