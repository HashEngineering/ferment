%ignore TimestampMillis::_0;
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
}