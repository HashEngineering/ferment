/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class TestEnum {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected TestEnum(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(TestEnum obj) {
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
        exampleJNI.delete_TestEnum(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setTag(TestEnum_Tag value) {
    exampleJNI.TestEnum_tag_set(swigCPtr, this, value.swigValue());
  }

  public TestEnum_Tag getTag() {
    return TestEnum_Tag.swigToEnum(exampleJNI.TestEnum_tag_get(swigCPtr, this));
  }

  public void setVariant1(String value) {
    exampleJNI.TestEnum_variant1_set(swigCPtr, this, value);
  }

  public String getVariant1() {
    return exampleJNI.TestEnum_variant1_get(swigCPtr, this);
  }

  public void setVariant3(TestEnum_Variant3_Body value) {
    exampleJNI.TestEnum_variant3_set(swigCPtr, this, TestEnum_Variant3_Body.getCPtr(value), value);
  }

  public TestEnum_Variant3_Body getVariant3() {
    long cPtr = exampleJNI.TestEnum_variant3_get(swigCPtr, this);
    return (cPtr == 0) ? null : new TestEnum_Variant3_Body(cPtr, false);
  }

  public void setVariant4(TestEnum_Variant4_Body value) {
    exampleJNI.TestEnum_variant4_set(swigCPtr, this, TestEnum_Variant4_Body.getCPtr(value), value);
  }

  public TestEnum_Variant4_Body getVariant4() {
    long cPtr = exampleJNI.TestEnum_variant4_get(swigCPtr, this);
    return (cPtr == 0) ? null : new TestEnum_Variant4_Body(cPtr, false);
  }

  public void setVariant5(TestEnum_Variant5_Body value) {
    exampleJNI.TestEnum_variant5_set(swigCPtr, this, TestEnum_Variant5_Body.getCPtr(value), value);
  }

  public TestEnum_Variant5_Body getVariant5() {
    long cPtr = exampleJNI.TestEnum_variant5_get(swigCPtr, this);
    return (cPtr == 0) ? null : new TestEnum_Variant5_Body(cPtr, false);
  }

}
