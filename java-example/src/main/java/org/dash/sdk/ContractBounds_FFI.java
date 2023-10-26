/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class ContractBounds_FFI {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ContractBounds_FFI(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ContractBounds_FFI obj) {
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
        exampleJNI.delete_ContractBounds_FFI(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setTag(ContractBounds_FFI_Tag value) {
    exampleJNI.ContractBounds_FFI_tag_set(swigCPtr, this, value.swigValue());
  }

  public ContractBounds_FFI_Tag getTag() {
    return ContractBounds_FFI_Tag.swigToEnum(exampleJNI.ContractBounds_FFI_tag_get(swigCPtr, this));
  }

  public void setSingle_contract(Identifier value) {
    exampleJNI.ContractBounds_FFI_single_contract_set(swigCPtr, this, Identifier.getCPtr(value), value);
  }

  public Identifier getSingle_contract() {
    long cPtr = exampleJNI.ContractBounds_FFI_single_contract_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Identifier(cPtr, false);
  }

  public void setSingle_contract_document_type(ContractBounds_FFI_SingleContractDocumentType_Body value) {
    exampleJNI.ContractBounds_FFI_single_contract_document_type_set(swigCPtr, this, ContractBounds_FFI_SingleContractDocumentType_Body.getCPtr(value), value);
  }

  public ContractBounds_FFI_SingleContractDocumentType_Body getSingle_contract_document_type() {
    long cPtr = exampleJNI.ContractBounds_FFI_single_contract_document_type_get(swigCPtr, this);
    return (cPtr == 0) ? null : new ContractBounds_FFI_SingleContractDocumentType_Body(cPtr, false);
  }

  public ContractBounds_FFI() {
    this(exampleJNI.new_ContractBounds_FFI(), true);
  }

}