/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class IdentityFactory {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected IdentityFactory(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(IdentityFactory obj) {
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
        exampleJNI.delete_IdentityFactory(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public IdentityFactory(IdentityFactory_TraitObject myFactory) {
    this(exampleJNI.new_IdentityFactory(IdentityFactory_TraitObject.getCPtr(myFactory), myFactory), true);
  }

  public Identity getIdentity(Identifier id) {
    return new Identity(exampleJNI.IdentityFactory_getIdentity(swigCPtr, this, Identifier.getCPtr(id), id), true);
  }

}
