/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class Identity {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Identity(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Identity obj) {
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
        exampleJNI.delete_Identity(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setTag(Identity_Tag value) {
    exampleJNI.Identity_tag_set(swigCPtr, this, value.swigValue());
  }

  public Identity_Tag getTag() {
    return Identity_Tag.swigToEnum(exampleJNI.Identity_tag_get(swigCPtr, this));
  }

  public void setV0(IdentityV0 value) {
    exampleJNI.Identity_v0_set(swigCPtr, this, IdentityV0.getCPtr(value), value);
  }

  public IdentityV0 getV0() {
    long cPtr = exampleJNI.Identity_v0_get(swigCPtr, this);
    return (cPtr == 0) ? null : new IdentityV0(cPtr, false);
  }

}