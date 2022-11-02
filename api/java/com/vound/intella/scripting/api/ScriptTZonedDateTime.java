/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.vound.intella.scripting.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-11-02")
public class ScriptTZonedDateTime implements org.apache.thrift.TBase<ScriptTZonedDateTime, ScriptTZonedDateTime._Fields>, java.io.Serializable, Cloneable, Comparable<ScriptTZonedDateTime> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ScriptTZonedDateTime");

  private static final org.apache.thrift.protocol.TField EPOCH_MILI_FIELD_DESC = new org.apache.thrift.protocol.TField("epochMili", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField ZONE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("zoneId", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ScriptTZonedDateTimeStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ScriptTZonedDateTimeTupleSchemeFactory();

  public long epochMili; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String zoneId; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    EPOCH_MILI((short)1, "epochMili"),
    ZONE_ID((short)2, "zoneId");

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
        case 1: // EPOCH_MILI
          return EPOCH_MILI;
        case 2: // ZONE_ID
          return ZONE_ID;
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
  private static final int __EPOCHMILI_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.EPOCH_MILI, new org.apache.thrift.meta_data.FieldMetaData("epochMili", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ZONE_ID, new org.apache.thrift.meta_data.FieldMetaData("zoneId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ScriptTZonedDateTime.class, metaDataMap);
  }

  public ScriptTZonedDateTime() {
  }

  public ScriptTZonedDateTime(
    long epochMili,
    java.lang.String zoneId)
  {
    this();
    this.epochMili = epochMili;
    setEpochMiliIsSet(true);
    this.zoneId = zoneId;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ScriptTZonedDateTime(ScriptTZonedDateTime other) {
    __isset_bitfield = other.__isset_bitfield;
    this.epochMili = other.epochMili;
    if (other.isSetZoneId()) {
      this.zoneId = other.zoneId;
    }
  }

  public ScriptTZonedDateTime deepCopy() {
    return new ScriptTZonedDateTime(this);
  }

  @Override
  public void clear() {
    setEpochMiliIsSet(false);
    this.epochMili = 0;
    this.zoneId = null;
  }

  public long getEpochMili() {
    return this.epochMili;
  }

  public ScriptTZonedDateTime setEpochMili(long epochMili) {
    this.epochMili = epochMili;
    setEpochMiliIsSet(true);
    return this;
  }

  public void unsetEpochMili() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __EPOCHMILI_ISSET_ID);
  }

  /** Returns true if field epochMili is set (has been assigned a value) and false otherwise */
  public boolean isSetEpochMili() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __EPOCHMILI_ISSET_ID);
  }

  public void setEpochMiliIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __EPOCHMILI_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getZoneId() {
    return this.zoneId;
  }

  public ScriptTZonedDateTime setZoneId(@org.apache.thrift.annotation.Nullable java.lang.String zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  public void unsetZoneId() {
    this.zoneId = null;
  }

  /** Returns true if field zoneId is set (has been assigned a value) and false otherwise */
  public boolean isSetZoneId() {
    return this.zoneId != null;
  }

  public void setZoneIdIsSet(boolean value) {
    if (!value) {
      this.zoneId = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case EPOCH_MILI:
      if (value == null) {
        unsetEpochMili();
      } else {
        setEpochMili((java.lang.Long)value);
      }
      break;

    case ZONE_ID:
      if (value == null) {
        unsetZoneId();
      } else {
        setZoneId((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case EPOCH_MILI:
      return getEpochMili();

    case ZONE_ID:
      return getZoneId();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case EPOCH_MILI:
      return isSetEpochMili();
    case ZONE_ID:
      return isSetZoneId();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof ScriptTZonedDateTime)
      return this.equals((ScriptTZonedDateTime)that);
    return false;
  }

  public boolean equals(ScriptTZonedDateTime that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_epochMili = true;
    boolean that_present_epochMili = true;
    if (this_present_epochMili || that_present_epochMili) {
      if (!(this_present_epochMili && that_present_epochMili))
        return false;
      if (this.epochMili != that.epochMili)
        return false;
    }

    boolean this_present_zoneId = true && this.isSetZoneId();
    boolean that_present_zoneId = true && that.isSetZoneId();
    if (this_present_zoneId || that_present_zoneId) {
      if (!(this_present_zoneId && that_present_zoneId))
        return false;
      if (!this.zoneId.equals(that.zoneId))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(epochMili);

    hashCode = hashCode * 8191 + ((isSetZoneId()) ? 131071 : 524287);
    if (isSetZoneId())
      hashCode = hashCode * 8191 + zoneId.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ScriptTZonedDateTime other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetEpochMili(), other.isSetEpochMili());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEpochMili()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.epochMili, other.epochMili);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetZoneId(), other.isSetZoneId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetZoneId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.zoneId, other.zoneId);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ScriptTZonedDateTime(");
    boolean first = true;

    sb.append("epochMili:");
    sb.append(this.epochMili);
    first = false;
    if (!first) sb.append(", ");
    sb.append("zoneId:");
    if (this.zoneId == null) {
      sb.append("null");
    } else {
      sb.append(this.zoneId);
    }
    first = false;
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ScriptTZonedDateTimeStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ScriptTZonedDateTimeStandardScheme getScheme() {
      return new ScriptTZonedDateTimeStandardScheme();
    }
  }

  private static class ScriptTZonedDateTimeStandardScheme extends org.apache.thrift.scheme.StandardScheme<ScriptTZonedDateTime> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ScriptTZonedDateTime struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // EPOCH_MILI
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.epochMili = iprot.readI64();
              struct.setEpochMiliIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ZONE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.zoneId = iprot.readString();
              struct.setZoneIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ScriptTZonedDateTime struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(EPOCH_MILI_FIELD_DESC);
      oprot.writeI64(struct.epochMili);
      oprot.writeFieldEnd();
      if (struct.zoneId != null) {
        oprot.writeFieldBegin(ZONE_ID_FIELD_DESC);
        oprot.writeString(struct.zoneId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ScriptTZonedDateTimeTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ScriptTZonedDateTimeTupleScheme getScheme() {
      return new ScriptTZonedDateTimeTupleScheme();
    }
  }

  private static class ScriptTZonedDateTimeTupleScheme extends org.apache.thrift.scheme.TupleScheme<ScriptTZonedDateTime> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ScriptTZonedDateTime struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetEpochMili()) {
        optionals.set(0);
      }
      if (struct.isSetZoneId()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetEpochMili()) {
        oprot.writeI64(struct.epochMili);
      }
      if (struct.isSetZoneId()) {
        oprot.writeString(struct.zoneId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ScriptTZonedDateTime struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.epochMili = iprot.readI64();
        struct.setEpochMiliIsSet(true);
      }
      if (incoming.get(1)) {
        struct.zoneId = iprot.readString();
        struct.setZoneIdIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

