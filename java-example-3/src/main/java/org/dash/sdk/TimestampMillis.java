/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class TimestampMillis {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected TimestampMillis(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(TimestampMillis obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        exampleJNI.delete_TimestampMillis(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void set_0(java.math.BigInteger value) {
    exampleJNI.TimestampMillis__0_set(swigCPtr, this, value);
  }

  public java.math.BigInteger get_0() {
    return exampleJNI.TimestampMillis__0_get(swigCPtr, this);
  }

  public TimestampMillis(int timestamp) {
    this(exampleJNI.new_TimestampMillis(timestamp), true);
  }

}
