%extend KeyID {
    KeyID(int id) {
        return KeyID_ctor(id);
    }
    ~KeyID() {
        KeyID_destroy($self);
    }
}