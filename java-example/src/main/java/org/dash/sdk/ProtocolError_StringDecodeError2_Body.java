/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class ProtocolError_StringDecodeError2_Body {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ProtocolError_StringDecodeError2_Body(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ProtocolError_StringDecodeError2_Body obj) {
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
        exampleJNI.delete_ProtocolError_StringDecodeError2_Body(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void set_0(String value) {
    exampleJNI.ProtocolError_StringDecodeError2_Body__0_set(swigCPtr, this, value);
  }

  public String get_0() {
    return exampleJNI.ProtocolError_StringDecodeError2_Body__0_get(swigCPtr, this);
  }

  public void set_1(long value) {
    exampleJNI.ProtocolError_StringDecodeError2_Body__1_set(swigCPtr, this, value);
  }

  public long get_1() {
    return exampleJNI.ProtocolError_StringDecodeError2_Body__1_get(swigCPtr, this);
  }

}