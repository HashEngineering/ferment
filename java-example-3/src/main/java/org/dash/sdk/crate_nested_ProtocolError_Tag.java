/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public final class crate_nested_ProtocolError_Tag {
  public final static crate_nested_ProtocolError_Tag crate_nested_ProtocolError_IdentifierError = new crate_nested_ProtocolError_Tag("crate_nested_ProtocolError_IdentifierError");
  public final static crate_nested_ProtocolError_Tag crate_nested_ProtocolError_StringDecodeError = new crate_nested_ProtocolError_Tag("crate_nested_ProtocolError_StringDecodeError");
  public final static crate_nested_ProtocolError_Tag crate_nested_ProtocolError_StringDecodeError2 = new crate_nested_ProtocolError_Tag("crate_nested_ProtocolError_StringDecodeError2");
  public final static crate_nested_ProtocolError_Tag crate_nested_ProtocolError_EmptyPublicKeyDataError = new crate_nested_ProtocolError_Tag("crate_nested_ProtocolError_EmptyPublicKeyDataError");
  public final static crate_nested_ProtocolError_Tag crate_nested_ProtocolError_MaxEncodedBytesReachedError = new crate_nested_ProtocolError_Tag("crate_nested_ProtocolError_MaxEncodedBytesReachedError");
  public final static crate_nested_ProtocolError_Tag crate_nested_ProtocolError_EncodingError = new crate_nested_ProtocolError_Tag("crate_nested_ProtocolError_EncodingError");
  public final static crate_nested_ProtocolError_Tag crate_nested_ProtocolError_EncodingError2 = new crate_nested_ProtocolError_Tag("crate_nested_ProtocolError_EncodingError2");
  public final static crate_nested_ProtocolError_Tag crate_nested_ProtocolError_DataContractNotPresentError = new crate_nested_ProtocolError_Tag("crate_nested_ProtocolError_DataContractNotPresentError");
  public final static crate_nested_ProtocolError_Tag crate_nested_ProtocolError_UnknownVersionMismatch = new crate_nested_ProtocolError_Tag("crate_nested_ProtocolError_UnknownVersionMismatch");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static crate_nested_ProtocolError_Tag swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + crate_nested_ProtocolError_Tag.class + " with value " + swigValue);
  }

  private crate_nested_ProtocolError_Tag(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private crate_nested_ProtocolError_Tag(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private crate_nested_ProtocolError_Tag(String swigName, crate_nested_ProtocolError_Tag swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static crate_nested_ProtocolError_Tag[] swigValues = { crate_nested_ProtocolError_IdentifierError, crate_nested_ProtocolError_StringDecodeError, crate_nested_ProtocolError_StringDecodeError2, crate_nested_ProtocolError_EmptyPublicKeyDataError, crate_nested_ProtocolError_MaxEncodedBytesReachedError, crate_nested_ProtocolError_EncodingError, crate_nested_ProtocolError_EncodingError2, crate_nested_ProtocolError_DataContractNotPresentError, crate_nested_ProtocolError_UnknownVersionMismatch };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}
