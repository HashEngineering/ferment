%ignore ferment_example_identity_identity_TimestampMillis::_0;
%rename(TimestampMillis) ferment_example_identity_identity_TimestampMillis;
// TODO: apply this to all types or specific types
// %typemap(javacode) SWIGTYPE %{
//   public boolean equals(Object obj) {
//     boolean equal = false;
//     if (obj instanceof $javaclassname) {
//       equal = ((($javaclassname)obj).swigCPtr == this.swigCPtr) || objectEquals(($javaclassname)obj);
//     }
//     return equal;
//   }
//   public int hashCode() {
//     return (int)swigCPtr;
//   }
// %}

%extend ferment_example_identity_identity_TimestampMillis {
    ferment_example_identity_identity_TimestampMillis() {
        return ferment_example_identity_identity_TimestampMillis_ctor(time(NULL) * 1000);
    }
    ferment_example_identity_identity_TimestampMillis(long long timestamp) {
        return ferment_example_identity_identity_TimestampMillis_ctor(timestamp);
    }
    ~ferment_example_identity_identity_TimestampMillis() {
        ferment_example_identity_identity_TimestampMillis_destroy($self);
    }

    long long toLong() {
        return $self ? $self->_0 : -1;
    }

//     bool isNull() {
//         return (uint64_t)$self < 10;
//     }

    bool objectEquals(ferment_example_identity_identity_TimestampMillis* other) {
        if ($self == other) return true;
        if ($self == nullptr || other == nullptr) return false;
        return $self->_0 == other->_0;
    }
}