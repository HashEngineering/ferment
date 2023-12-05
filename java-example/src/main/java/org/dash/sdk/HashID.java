/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class HashID {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected HashID(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(HashID obj) {
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
        exampleJNI.delete_HashID(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void set_0(byte[] value) {
    exampleJNI.HashID__0_set(swigCPtr, this, value);
  }

  public byte[] get_0() {
    return exampleJNI.HashID__0_get(swigCPtr, this);
  }

  public HashID(byte[] o_0) {
    this(exampleJNI.new_HashID(o_0), true);
  }

}
