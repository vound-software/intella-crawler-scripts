/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.vound.intella.scripting.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-10-21")
public class FoundItemResult implements org.apache.thrift.TBase<FoundItemResult, FoundItemResult._Fields>, java.io.Serializable, Cloneable, Comparable<FoundItemResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("FoundItemResult");

  private static final org.apache.thrift.protocol.TField ACTION_FIELD_DESC = new org.apache.thrift.protocol.TField("action", org.apache.thrift.protocol.TType.I32, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new FoundItemResultStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new FoundItemResultTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable Action action; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ACTION((short)1, "action");

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
        case 1: // ACTION
          return ACTION;
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

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.ACTION};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ACTION, new org.apache.thrift.meta_data.FieldMetaData("action", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.ENUM        , "Action")));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(FoundItemResult.class, metaDataMap);
  }

  public FoundItemResult() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public FoundItemResult(FoundItemResult other) {
    if (other.isSetAction()) {
      this.action = other.action;
    }
  }

  public FoundItemResult deepCopy() {
    return new FoundItemResult(this);
  }

  @Override
  public void clear() {
    this.action = null;
  }

  @org.apache.thrift.annotation.Nullable
  public Action getAction() {
    return this.action;
  }

  public FoundItemResult setAction(@org.apache.thrift.annotation.Nullable Action action) {
    this.action = action;
    return this;
  }

  public void unsetAction() {
    this.action = null;
  }

  /** Returns true if field action is set (has been assigned a value) and false otherwise */
  public boolean isSetAction() {
    return this.action != null;
  }

  public void setActionIsSet(boolean value) {
    if (!value) {
      this.action = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case ACTION:
      if (value == null) {
        unsetAction();
      } else {
        setAction((Action)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ACTION:
      return getAction();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ACTION:
      return isSetAction();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof FoundItemResult)
      return this.equals((FoundItemResult)that);
    return false;
  }

  public boolean equals(FoundItemResult that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_action = true && this.isSetAction();
    boolean that_present_action = true && that.isSetAction();
    if (this_present_action || that_present_action) {
      if (!(this_present_action && that_present_action))
        return false;
      if (!this.action.equals(that.action))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetAction()) ? 131071 : 524287);
    if (isSetAction())
      hashCode = hashCode * 8191 + action.getValue();

    return hashCode;
  }

  @Override
  public int compareTo(FoundItemResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetAction(), other.isSetAction());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAction()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.action, other.action);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("FoundItemResult(");
    boolean first = true;

    if (isSetAction()) {
      sb.append("action:");
      if (this.action == null) {
        sb.append("null");
      } else {
        sb.append(this.action);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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

  private static class FoundItemResultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public FoundItemResultStandardScheme getScheme() {
      return new FoundItemResultStandardScheme();
    }
  }

  private static class FoundItemResultStandardScheme extends org.apache.thrift.scheme.StandardScheme<FoundItemResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, FoundItemResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ACTION
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.action = com.vound.intella.scripting.api.Action.findByValue(iprot.readI32());
              struct.setActionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, FoundItemResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.action != null) {
        if (struct.isSetAction()) {
          oprot.writeFieldBegin(ACTION_FIELD_DESC);
          oprot.writeI32(struct.action.getValue());
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class FoundItemResultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public FoundItemResultTupleScheme getScheme() {
      return new FoundItemResultTupleScheme();
    }
  }

  private static class FoundItemResultTupleScheme extends org.apache.thrift.scheme.TupleScheme<FoundItemResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, FoundItemResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAction()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetAction()) {
        oprot.writeI32(struct.action.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, FoundItemResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.action = com.vound.intella.scripting.api.Action.findByValue(iprot.readI32());
        struct.setActionIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
