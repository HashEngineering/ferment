/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public final class IdentityPublicKey_Tag {
  public final static IdentityPublicKey_Tag crate_identity_identity_IdentityPublicKey_V0 = new IdentityPublicKey_Tag("crate_identity_identity_IdentityPublicKey_V0");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static IdentityPublicKey_Tag swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + IdentityPublicKey_Tag.class + " with value " + swigValue);
  }

  private IdentityPublicKey_Tag(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private IdentityPublicKey_Tag(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private IdentityPublicKey_Tag(String swigName, IdentityPublicKey_Tag swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static IdentityPublicKey_Tag[] swigValues = { crate_identity_identity_IdentityPublicKey_V0 };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

