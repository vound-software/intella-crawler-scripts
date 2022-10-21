/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.vound.intella.scripting.api;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-10-21")
public enum CustomColumnType implements org.apache.thrift.TEnum {
  String(0),
  Integer(1),
  Long(2),
  Double(3),
  Boolean(4),
  DateTime(5);

  private final int value;

  private CustomColumnType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static CustomColumnType findByValue(int value) { 
    switch (value) {
      case 0:
        return String;
      case 1:
        return Integer;
      case 2:
        return Long;
      case 3:
        return Double;
      case 4:
        return Boolean;
      case 5:
        return DateTime;
      default:
        return null;
    }
  }
}
