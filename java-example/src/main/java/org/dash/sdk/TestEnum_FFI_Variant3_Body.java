/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class TestEnum_FFI_Variant3_Body {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected TestEnum_FFI_Variant3_Body(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(TestEnum_FFI_Variant3_Body obj) {
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
        exampleJNI.delete_TestEnum_FFI_Variant3_Body(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void set_0(HashID value) {
    exampleJNI.TestEnum_FFI_Variant3_Body__0_set(swigCPtr, this, HashID.getCPtr(value), value);
  }

  public HashID get_0() {
    long cPtr = exampleJNI.TestEnum_FFI_Variant3_Body__0_get(swigCPtr, this);
    return (cPtr == 0) ? null : new HashID(cPtr, false);
  }

  public void set_1(long value) {
    exampleJNI.TestEnum_FFI_Variant3_Body__1_set(swigCPtr, this, value);
  }

  public long get_1() {
    return exampleJNI.TestEnum_FFI_Variant3_Body__1_get(swigCPtr, this);
  }

  public TestEnum_FFI_Variant3_Body() {
    this(exampleJNI.new_TestEnum_FFI_Variant3_Body(), true);
  }

}
