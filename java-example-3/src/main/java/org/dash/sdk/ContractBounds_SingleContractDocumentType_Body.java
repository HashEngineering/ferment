/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public class ContractBounds_SingleContractDocumentType_Body {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ContractBounds_SingleContractDocumentType_Body(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ContractBounds_SingleContractDocumentType_Body obj) {
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
        exampleJNI.delete_ContractBounds_SingleContractDocumentType_Body(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setId(Identifier value) {
    exampleJNI.ContractBounds_SingleContractDocumentType_Body_id_set(swigCPtr, this, Identifier.getCPtr(value), value);
  }

  public Identifier getId() {
    long cPtr = exampleJNI.ContractBounds_SingleContractDocumentType_Body_id_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Identifier(cPtr, false);
  }

  public void setDocument_type_name(String value) {
    exampleJNI.ContractBounds_SingleContractDocumentType_Body_document_type_name_set(swigCPtr, this, value);
  }

  public String getDocument_type_name() {
    return exampleJNI.ContractBounds_SingleContractDocumentType_Body_document_type_name_get(swigCPtr, this);
  }

}