%ignore TimestampMillis::_0;
// TODO: apply this to all types or specific types
%typemap(javacode) TimestampMillis %{
  public boolean equals(Object obj) {
    boolean equal = false;
    if (obj instanceof $javaclassname) {
      equal = ((($javaclassname)obj).swigCPtr == this.swigCPtr) || objectEquals(($javaclassname)obj);
    }
    return equal;
  }
  public int hashCode() {
    return (int)swigCPtr;
  }
%}

%extend TimestampMillis {
    TimestampMillis() {
        return TimestampMillis_ctor(time(NULL) * 1000);
    }
    TimestampMillis(long long timestamp) {
        return TimestampMillis_ctor(timestamp);
    }
    ~TimestampMillis() {
        TimestampMillis_destroy($self);
    }

    long long toLong() {
        return $self->_0;
    }

    bool objectEquals(TimestampMillis* other) {
        return $self->_0 == other->_0;
    }
}