%extend TimestampMillis {
    TimestampMillis(long timestamp) {
        return TimestampMillis_ctor(timestamp);
    }
    ~TimestampMillis() {
        TimestampMillis_destroy($self);
    }
}