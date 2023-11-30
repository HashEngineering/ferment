/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class TestStruct_FFI {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected TestStruct_FFI(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(TestStruct_FFI obj) {
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
        exampleJNI.delete_TestStruct_FFI(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setVec_u8(SWIGTYPE_p_Vec_u8_FFI value) {
    exampleJNI.TestStruct_FFI_vec_u8_set(swigCPtr, this, SWIGTYPE_p_Vec_u8_FFI.getCPtr(value));
  }

  public SWIGTYPE_p_Vec_u8_FFI getVec_u8() {
    long cPtr = exampleJNI.TestStruct_FFI_vec_u8_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_Vec_u8_FFI(cPtr, false);
  }

  public void setVec_u32(Vec_u32_FFI value) {
    exampleJNI.TestStruct_FFI_vec_u32_set(swigCPtr, this, Vec_u32_FFI.getCPtr(value), value);
  }

  public Vec_u32_FFI getVec_u32() {
    long cPtr = exampleJNI.TestStruct_FFI_vec_u32_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Vec_u32_FFI(cPtr, false);
  }

  public void setVec_vec_u32(Vec_Vec_u32_FFI value) {
    exampleJNI.TestStruct_FFI_vec_vec_u32_set(swigCPtr, this, Vec_Vec_u32_FFI.getCPtr(value), value);
  }

  public Vec_Vec_u32_FFI getVec_vec_u32() {
    long cPtr = exampleJNI.TestStruct_FFI_vec_vec_u32_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Vec_Vec_u32_FFI(cPtr, false);
  }

  public void setMap_key_simple_value_simple(std_collections_Map_keys_u32_values_u32_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_simple_value_simple_set(swigCPtr, this, std_collections_Map_keys_u32_values_u32_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_u32_values_u32_FFI getMap_key_simple_value_simple() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_simple_value_simple_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_u32_values_u32_FFI(cPtr, false);
  }

  public void setMap_key_simple_value_complex(std_collections_Map_keys_u32_values_crate_nested_HashID_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_simple_value_complex_set(swigCPtr, this, std_collections_Map_keys_u32_values_crate_nested_HashID_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_u32_values_crate_nested_HashID_FFI getMap_key_simple_value_complex() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_simple_value_complex_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_u32_values_crate_nested_HashID_FFI(cPtr, false);
  }

  public void setMap_key_simple_value_vec_simple(std_collections_Map_keys_u32_values_Vec_u32_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_simple_value_vec_simple_set(swigCPtr, this, std_collections_Map_keys_u32_values_Vec_u32_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_u32_values_Vec_u32_FFI getMap_key_simple_value_vec_simple() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_simple_value_vec_simple_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_u32_values_Vec_u32_FFI(cPtr, false);
  }

  public void setMap_key_simple_value_vec_complex(std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_simple_value_vec_complex_set(swigCPtr, this, std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI getMap_key_simple_value_vec_complex() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_simple_value_vec_complex_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI(cPtr, false);
  }

  public void setMap_key_simple_value_map_key_simple_value_simple(std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_u32_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_simple_value_map_key_simple_value_simple_set(swigCPtr, this, std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_u32_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_u32_FFI getMap_key_simple_value_map_key_simple_value_simple() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_simple_value_map_key_simple_value_simple_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_u32_FFI(cPtr, false);
  }

  public void setMap_key_simple_value_map_key_simple_value_complex(std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_crate_nested_HashID_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_simple_value_map_key_simple_value_complex_set(swigCPtr, this, std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_crate_nested_HashID_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_crate_nested_HashID_FFI getMap_key_simple_value_map_key_simple_value_complex() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_simple_value_map_key_simple_value_complex_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_crate_nested_HashID_FFI(cPtr, false);
  }

  public void setMap_key_simple_value_map_key_simple_value_vec_simple(std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_Vec_u32_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_simple_value_map_key_simple_value_vec_simple_set(swigCPtr, this, std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_Vec_u32_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_Vec_u32_FFI getMap_key_simple_value_map_key_simple_value_vec_simple() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_simple_value_map_key_simple_value_vec_simple_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_Vec_u32_FFI(cPtr, false);
  }

  public void setMap_key_simple_value_map_key_simple_value_vec_complex(std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_simple_value_map_key_simple_value_vec_complex_set(swigCPtr, this, std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI getMap_key_simple_value_map_key_simple_value_vec_complex() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_simple_value_map_key_simple_value_vec_complex_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI(cPtr, false);
  }

  public void setMap_key_complex_value_simple(std_collections_Map_keys_crate_nested_HashID_values_u32_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_complex_value_simple_set(swigCPtr, this, std_collections_Map_keys_crate_nested_HashID_values_u32_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_crate_nested_HashID_values_u32_FFI getMap_key_complex_value_simple() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_complex_value_simple_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_crate_nested_HashID_values_u32_FFI(cPtr, false);
  }

  public void setMap_key_complex_value_complex(std_collections_Map_keys_crate_nested_HashID_values_crate_nested_HashID_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_complex_value_complex_set(swigCPtr, this, std_collections_Map_keys_crate_nested_HashID_values_crate_nested_HashID_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_crate_nested_HashID_values_crate_nested_HashID_FFI getMap_key_complex_value_complex() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_complex_value_complex_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_crate_nested_HashID_values_crate_nested_HashID_FFI(cPtr, false);
  }

  public void setMap_key_complex_value_vec_simple(std_collections_Map_keys_crate_nested_HashID_values_Vec_u32_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_complex_value_vec_simple_set(swigCPtr, this, std_collections_Map_keys_crate_nested_HashID_values_Vec_u32_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_crate_nested_HashID_values_Vec_u32_FFI getMap_key_complex_value_vec_simple() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_complex_value_vec_simple_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_crate_nested_HashID_values_Vec_u32_FFI(cPtr, false);
  }

  public void setMap_key_complex_value_vec_complex(std_collections_Map_keys_crate_nested_HashID_values_Vec_crate_nested_HashID_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_complex_value_vec_complex_set(swigCPtr, this, std_collections_Map_keys_crate_nested_HashID_values_Vec_crate_nested_HashID_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_crate_nested_HashID_values_Vec_crate_nested_HashID_FFI getMap_key_complex_value_vec_complex() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_complex_value_vec_complex_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_crate_nested_HashID_values_Vec_crate_nested_HashID_FFI(cPtr, false);
  }

  public void setMap_key_complex_value_map_key_simple_value_vec_simple(std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_Vec_u32_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_complex_value_map_key_simple_value_vec_simple_set(swigCPtr, this, std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_Vec_u32_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_Vec_u32_FFI getMap_key_complex_value_map_key_simple_value_vec_simple() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_complex_value_map_key_simple_value_vec_simple_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_Vec_u32_FFI(cPtr, false);
  }

  public void setMap_key_complex_value_map_key_simple_value_vec_complex(std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_complex_value_map_key_simple_value_vec_complex_set(swigCPtr, this, std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI getMap_key_complex_value_map_key_simple_value_vec_complex() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_complex_value_map_key_simple_value_vec_complex_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_Vec_crate_nested_HashID_FFI(cPtr, false);
  }

  public void setMap_key_complex_value_map_key_simple_value_map_key_complex_value_complex(std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_std_collections_Map_keys_crate_nested_HashID_values_crate_nested_HashID_FFI value) {
    exampleJNI.TestStruct_FFI_map_key_complex_value_map_key_simple_value_map_key_complex_value_complex_set(swigCPtr, this, std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_std_collections_Map_keys_crate_nested_HashID_values_crate_nested_HashID_FFI.getCPtr(value), value);
  }

  public std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_std_collections_Map_keys_crate_nested_HashID_values_crate_nested_HashID_FFI getMap_key_complex_value_map_key_simple_value_map_key_complex_value_complex() {
    long cPtr = exampleJNI.TestStruct_FFI_map_key_complex_value_map_key_simple_value_map_key_complex_value_complex_get(swigCPtr, this);
    return (cPtr == 0) ? null : new std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_std_collections_Map_keys_crate_nested_HashID_values_crate_nested_HashID_FFI(cPtr, false);
  }

  public TestStruct_FFI() {
    this(exampleJNI.new_TestStruct_FFI(), true);
  }

}
