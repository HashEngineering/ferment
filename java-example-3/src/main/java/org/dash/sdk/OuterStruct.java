/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

import org.dash.sdk.base.BaseObject;

public class OuterStruct extends BaseObject {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected OuterStruct(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OuterStruct obj) {
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
        exampleJNI.delete_OuterStruct(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  protected long getCPointer() {
    return swigCPtr;
  }

  public void setFirst(InnerStruct value) {
    exampleJNI.OuterStruct_first_set(swigCPtr, this, InnerStruct.getCPtr(value), value);
  }

  public InnerStruct getFirst() {
    long cPtr = exampleJNI.OuterStruct_first_get(swigCPtr, this);
    return (cPtr == 0) ? null : new InnerStruct(cPtr, false);
  }

  public void setSecond(InnerStruct value) {
    exampleJNI.OuterStruct_second_set(swigCPtr, this, InnerStruct.getCPtr(value), value);
  }

  public InnerStruct getSecond() {
    long cPtr = exampleJNI.OuterStruct_second_get(swigCPtr, this);
    return (cPtr == 0) ? null : new InnerStruct(cPtr, false);
  }

  public OuterStruct(InnerStruct is1, InnerStruct is2) {
    this(exampleJNI.new_OuterStruct__SWIG_0(InnerStruct.getCPtr(is1), is1, InnerStruct.getCPtr(is2), is2), true);
  }

  public OuterStruct(int a, int b, int c, int d) {
    this(exampleJNI.new_OuterStruct__SWIG_1(a, b, c, d), true);
  }

}
