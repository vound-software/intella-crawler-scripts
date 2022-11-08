/**
 * MIT License
 *
 * Copyright (c) 2022 Vound
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.vound.intella.scripting.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-11-08")
public class CustomColumnValue extends org.apache.thrift.TUnion<CustomColumnValue, CustomColumnValue._Fields> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CustomColumnValue");
  private static final org.apache.thrift.protocol.TField VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("value", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField INT_VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("intValue", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField LONG_VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("longValue", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField DOUBLE_VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("doubleValue", org.apache.thrift.protocol.TType.DOUBLE, (short)4);
  private static final org.apache.thrift.protocol.TField BOOL_VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("boolValue", org.apache.thrift.protocol.TType.BOOL, (short)5);
  private static final org.apache.thrift.protocol.TField DATE_TIME_VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("dateTimeValue", org.apache.thrift.protocol.TType.STRUCT, (short)6);

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    VALUE((short)1, "value"),
    INT_VALUE((short)2, "intValue"),
    LONG_VALUE((short)3, "longValue"),
    DOUBLE_VALUE((short)4, "doubleValue"),
    BOOL_VALUE((short)5, "boolValue"),
    DATE_TIME_VALUE((short)6, "dateTimeValue");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // VALUE
          return VALUE;
        case 2: // INT_VALUE
          return INT_VALUE;
        case 3: // LONG_VALUE
          return LONG_VALUE;
        case 4: // DOUBLE_VALUE
          return DOUBLE_VALUE;
        case 5: // BOOL_VALUE
          return BOOL_VALUE;
        case 6: // DATE_TIME_VALUE
          return DATE_TIME_VALUE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.VALUE, new org.apache.thrift.meta_data.FieldMetaData("value", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.INT_VALUE, new org.apache.thrift.meta_data.FieldMetaData("intValue", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.LONG_VALUE, new org.apache.thrift.meta_data.FieldMetaData("longValue", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.DOUBLE_VALUE, new org.apache.thrift.meta_data.FieldMetaData("doubleValue", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.BOOL_VALUE, new org.apache.thrift.meta_data.FieldMetaData("boolValue", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.DATE_TIME_VALUE, new org.apache.thrift.meta_data.FieldMetaData("dateTimeValue", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ScriptTZonedDateTime.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CustomColumnValue.class, metaDataMap);
  }

  public CustomColumnValue() {
    super();
  }

  public CustomColumnValue(_Fields setField, java.lang.Object value) {
    super(setField, value);
  }

  public CustomColumnValue(CustomColumnValue other) {
    super(other);
  }
  public CustomColumnValue deepCopy() {
    return new CustomColumnValue(this);
  }

  public static CustomColumnValue value(java.lang.String value) {
    CustomColumnValue x = new CustomColumnValue();
    x.setValue(value);
    return x;
  }

  public static CustomColumnValue intValue(int value) {
    CustomColumnValue x = new CustomColumnValue();
    x.setIntValue(value);
    return x;
  }

  public static CustomColumnValue longValue(long value) {
    CustomColumnValue x = new CustomColumnValue();
    x.setLongValue(value);
    return x;
  }

  public static CustomColumnValue doubleValue(double value) {
    CustomColumnValue x = new CustomColumnValue();
    x.setDoubleValue(value);
    return x;
  }

  public static CustomColumnValue boolValue(boolean value) {
    CustomColumnValue x = new CustomColumnValue();
    x.setBoolValue(value);
    return x;
  }

  public static CustomColumnValue dateTimeValue(ScriptTZonedDateTime value) {
    CustomColumnValue x = new CustomColumnValue();
    x.setDateTimeValue(value);
    return x;
  }


  @Override
  protected void checkType(_Fields setField, java.lang.Object value) throws java.lang.ClassCastException {
    switch (setField) {
      case VALUE:
        if (value instanceof java.lang.String) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type java.lang.String for field 'value', but got " + value.getClass().getSimpleName());
      case INT_VALUE:
        if (value instanceof java.lang.Integer) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type java.lang.Integer for field 'intValue', but got " + value.getClass().getSimpleName());
      case LONG_VALUE:
        if (value instanceof java.lang.Long) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type java.lang.Long for field 'longValue', but got " + value.getClass().getSimpleName());
      case DOUBLE_VALUE:
        if (value instanceof java.lang.Double) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type java.lang.Double for field 'doubleValue', but got " + value.getClass().getSimpleName());
      case BOOL_VALUE:
        if (value instanceof java.lang.Boolean) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type java.lang.Boolean for field 'boolValue', but got " + value.getClass().getSimpleName());
      case DATE_TIME_VALUE:
        if (value instanceof ScriptTZonedDateTime) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type ScriptTZonedDateTime for field 'dateTimeValue', but got " + value.getClass().getSimpleName());
      default:
        throw new java.lang.IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected java.lang.Object standardSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TField field) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(field.id);
    if (setField != null) {
      switch (setField) {
        case VALUE:
          if (field.type == VALUE_FIELD_DESC.type) {
            java.lang.String value;
            value = iprot.readString();
            return value;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case INT_VALUE:
          if (field.type == INT_VALUE_FIELD_DESC.type) {
            java.lang.Integer intValue;
            intValue = iprot.readI32();
            return intValue;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case LONG_VALUE:
          if (field.type == LONG_VALUE_FIELD_DESC.type) {
            java.lang.Long longValue;
            longValue = iprot.readI64();
            return longValue;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case DOUBLE_VALUE:
          if (field.type == DOUBLE_VALUE_FIELD_DESC.type) {
            java.lang.Double doubleValue;
            doubleValue = iprot.readDouble();
            return doubleValue;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case BOOL_VALUE:
          if (field.type == BOOL_VALUE_FIELD_DESC.type) {
            java.lang.Boolean boolValue;
            boolValue = iprot.readBool();
            return boolValue;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case DATE_TIME_VALUE:
          if (field.type == DATE_TIME_VALUE_FIELD_DESC.type) {
            ScriptTZonedDateTime dateTimeValue;
            dateTimeValue = new ScriptTZonedDateTime();
            dateTimeValue.read(iprot);
            return dateTimeValue;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        default:
          throw new java.lang.IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      return null;
    }
  }

  @Override
  protected void standardSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case VALUE:
        java.lang.String value = (java.lang.String)value_;
        oprot.writeString(value);
        return;
      case INT_VALUE:
        java.lang.Integer intValue = (java.lang.Integer)value_;
        oprot.writeI32(intValue);
        return;
      case LONG_VALUE:
        java.lang.Long longValue = (java.lang.Long)value_;
        oprot.writeI64(longValue);
        return;
      case DOUBLE_VALUE:
        java.lang.Double doubleValue = (java.lang.Double)value_;
        oprot.writeDouble(doubleValue);
        return;
      case BOOL_VALUE:
        java.lang.Boolean boolValue = (java.lang.Boolean)value_;
        oprot.writeBool(boolValue);
        return;
      case DATE_TIME_VALUE:
        ScriptTZonedDateTime dateTimeValue = (ScriptTZonedDateTime)value_;
        dateTimeValue.write(oprot);
        return;
      default:
        throw new java.lang.IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected java.lang.Object tupleSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, short fieldID) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(fieldID);
    if (setField != null) {
      switch (setField) {
        case VALUE:
          java.lang.String value;
          value = iprot.readString();
          return value;
        case INT_VALUE:
          java.lang.Integer intValue;
          intValue = iprot.readI32();
          return intValue;
        case LONG_VALUE:
          java.lang.Long longValue;
          longValue = iprot.readI64();
          return longValue;
        case DOUBLE_VALUE:
          java.lang.Double doubleValue;
          doubleValue = iprot.readDouble();
          return doubleValue;
        case BOOL_VALUE:
          java.lang.Boolean boolValue;
          boolValue = iprot.readBool();
          return boolValue;
        case DATE_TIME_VALUE:
          ScriptTZonedDateTime dateTimeValue;
          dateTimeValue = new ScriptTZonedDateTime();
          dateTimeValue.read(iprot);
          return dateTimeValue;
        default:
          throw new java.lang.IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      throw new org.apache.thrift.protocol.TProtocolException("Couldn't find a field with field id " + fieldID);
    }
  }

  @Override
  protected void tupleSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case VALUE:
        java.lang.String value = (java.lang.String)value_;
        oprot.writeString(value);
        return;
      case INT_VALUE:
        java.lang.Integer intValue = (java.lang.Integer)value_;
        oprot.writeI32(intValue);
        return;
      case LONG_VALUE:
        java.lang.Long longValue = (java.lang.Long)value_;
        oprot.writeI64(longValue);
        return;
      case DOUBLE_VALUE:
        java.lang.Double doubleValue = (java.lang.Double)value_;
        oprot.writeDouble(doubleValue);
        return;
      case BOOL_VALUE:
        java.lang.Boolean boolValue = (java.lang.Boolean)value_;
        oprot.writeBool(boolValue);
        return;
      case DATE_TIME_VALUE:
        ScriptTZonedDateTime dateTimeValue = (ScriptTZonedDateTime)value_;
        dateTimeValue.write(oprot);
        return;
      default:
        throw new java.lang.IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TField getFieldDesc(_Fields setField) {
    switch (setField) {
      case VALUE:
        return VALUE_FIELD_DESC;
      case INT_VALUE:
        return INT_VALUE_FIELD_DESC;
      case LONG_VALUE:
        return LONG_VALUE_FIELD_DESC;
      case DOUBLE_VALUE:
        return DOUBLE_VALUE_FIELD_DESC;
      case BOOL_VALUE:
        return BOOL_VALUE_FIELD_DESC;
      case DATE_TIME_VALUE:
        return DATE_TIME_VALUE_FIELD_DESC;
      default:
        throw new java.lang.IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TStruct getStructDesc() {
    return STRUCT_DESC;
  }

  @Override
  protected _Fields enumForId(short id) {
    return _Fields.findByThriftIdOrThrow(id);
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public java.lang.String getValue() {
    if (getSetField() == _Fields.VALUE) {
      return (java.lang.String)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'value' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setValue(java.lang.String value) {
    setField_ = _Fields.VALUE;
    value_ = java.util.Objects.requireNonNull(value,"_Fields.VALUE");
  }

  public int getIntValue() {
    if (getSetField() == _Fields.INT_VALUE) {
      return (java.lang.Integer)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'intValue' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setIntValue(int value) {
    setField_ = _Fields.INT_VALUE;
    value_ = value;
  }

  public long getLongValue() {
    if (getSetField() == _Fields.LONG_VALUE) {
      return (java.lang.Long)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'longValue' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setLongValue(long value) {
    setField_ = _Fields.LONG_VALUE;
    value_ = value;
  }

  public double getDoubleValue() {
    if (getSetField() == _Fields.DOUBLE_VALUE) {
      return (java.lang.Double)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'doubleValue' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setDoubleValue(double value) {
    setField_ = _Fields.DOUBLE_VALUE;
    value_ = value;
  }

  public boolean getBoolValue() {
    if (getSetField() == _Fields.BOOL_VALUE) {
      return (java.lang.Boolean)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'boolValue' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setBoolValue(boolean value) {
    setField_ = _Fields.BOOL_VALUE;
    value_ = value;
  }

  public ScriptTZonedDateTime getDateTimeValue() {
    if (getSetField() == _Fields.DATE_TIME_VALUE) {
      return (ScriptTZonedDateTime)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'dateTimeValue' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setDateTimeValue(ScriptTZonedDateTime value) {
    setField_ = _Fields.DATE_TIME_VALUE;
    value_ = java.util.Objects.requireNonNull(value,"_Fields.DATE_TIME_VALUE");
  }

  public boolean isSetValue() {
    return setField_ == _Fields.VALUE;
  }


  public boolean isSetIntValue() {
    return setField_ == _Fields.INT_VALUE;
  }


  public boolean isSetLongValue() {
    return setField_ == _Fields.LONG_VALUE;
  }


  public boolean isSetDoubleValue() {
    return setField_ == _Fields.DOUBLE_VALUE;
  }


  public boolean isSetBoolValue() {
    return setField_ == _Fields.BOOL_VALUE;
  }


  public boolean isSetDateTimeValue() {
    return setField_ == _Fields.DATE_TIME_VALUE;
  }


  public boolean equals(java.lang.Object other) {
    if (other instanceof CustomColumnValue) {
      return equals((CustomColumnValue)other);
    } else {
      return false;
    }
  }

  public boolean equals(CustomColumnValue other) {
    return other != null && getSetField() == other.getSetField() && getFieldValue().equals(other.getFieldValue());
  }

  @Override
  public int compareTo(CustomColumnValue other) {
    int lastComparison = org.apache.thrift.TBaseHelper.compareTo(getSetField(), other.getSetField());
    if (lastComparison == 0) {
      return org.apache.thrift.TBaseHelper.compareTo(getFieldValue(), other.getFieldValue());
    }
    return lastComparison;
  }


  @Override
  public int hashCode() {
    java.util.List<java.lang.Object> list = new java.util.ArrayList<java.lang.Object>();
    list.add(this.getClass().getName());
    org.apache.thrift.TFieldIdEnum setField = getSetField();
    if (setField != null) {
      list.add(setField.getThriftFieldId());
      java.lang.Object value = getFieldValue();
      if (value instanceof org.apache.thrift.TEnum) {
        list.add(((org.apache.thrift.TEnum)getFieldValue()).getValue());
      } else {
        list.add(value);
      }
    }
    return list.hashCode();
  }
  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


}
