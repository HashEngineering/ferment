/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class ArrayOfArraysOfHashes {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ArrayOfArraysOfHashes(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ArrayOfArraysOfHashes obj) {
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
        exampleJNI.delete_ArrayOfArraysOfHashes(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void set_0(Vec_Vec_crate_nested_HashID value) {
    exampleJNI.ArrayOfArraysOfHashes__0_set(swigCPtr, this, Vec_Vec_crate_nested_HashID.getCPtr(value), value);
  }

  public Vec_Vec_crate_nested_HashID get_0() {
    long cPtr = exampleJNI.ArrayOfArraysOfHashes__0_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Vec_Vec_crate_nested_HashID(cPtr, false);
  }

}