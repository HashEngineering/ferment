/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

import org.dash.sdk.base.BaseObject;

public class Revision extends BaseObject {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Revision(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Revision obj) {
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
        exampleJNI.delete_Revision(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  protected long getCPointer() {
    return swigCPtr;
  }

  public Revision() {
    this(exampleJNI.new_Revision__SWIG_0(), true);
  }

  public Revision(long timestamp) {
    this(exampleJNI.new_Revision__SWIG_1(timestamp), true);
  }

  public long toLong() {
    return exampleJNI.Revision_toLong(swigCPtr, this);
  }

}
