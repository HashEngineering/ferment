/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.dash.sdk;

public final class ContractBounds_Tag {
  public final static ContractBounds_Tag ContractBounds_SingleContract = new ContractBounds_Tag("ContractBounds_SingleContract");
  public final static ContractBounds_Tag ContractBounds_SingleContractDocumentType = new ContractBounds_Tag("ContractBounds_SingleContractDocumentType");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static ContractBounds_Tag swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + ContractBounds_Tag.class + " with value " + swigValue);
  }

  private ContractBounds_Tag(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private ContractBounds_Tag(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private ContractBounds_Tag(String swigName, ContractBounds_Tag swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static ContractBounds_Tag[] swigValues = { ContractBounds_SingleContract, ContractBounds_SingleContractDocumentType };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

