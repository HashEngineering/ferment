/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI obj) {
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
        exampleJNI.delete_std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setCount(long value) {
    exampleJNI.std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_count_set(swigCPtr, this, value);
  }

  public long getCount() {
    return exampleJNI.std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_count_get(swigCPtr, this);
  }

  public void setKeys(SWIGTYPE_p_p_KeyID_FFI value) {
    exampleJNI.std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_keys_set(swigCPtr, this, SWIGTYPE_p_p_KeyID_FFI.getCPtr(value));
  }

  public SWIGTYPE_p_p_KeyID_FFI getKeys() {
    long cPtr = exampleJNI.std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_keys_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_p_KeyID_FFI(cPtr, false);
  }

  public void setValues(SWIGTYPE_p_p_IdentityPublicKey_FFI value) {
    exampleJNI.std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_values_set(swigCPtr, this, SWIGTYPE_p_p_IdentityPublicKey_FFI.getCPtr(value));
  }

  public SWIGTYPE_p_p_IdentityPublicKey_FFI getValues() {
    long cPtr = exampleJNI.std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI_values_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_p_IdentityPublicKey_FFI(cPtr, false);
  }

  public std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI() {
    this(exampleJNI.new_std_collections_Map_keys_crate_nested_KeyID_values_crate_nested_IdentityPublicKey_FFI(), true);
  }

}