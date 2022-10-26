/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.vound.intella.scripting.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-10-26")
public class ProcessedItemResult implements org.apache.thrift.TBase<ProcessedItemResult, ProcessedItemResult._Fields>, java.io.Serializable, Cloneable, Comparable<ProcessedItemResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ProcessedItemResult");

  private static final org.apache.thrift.protocol.TField ACTION_FIELD_DESC = new org.apache.thrift.protocol.TField("action", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField MODIFIED_ITEM_FIELD_DESC = new org.apache.thrift.protocol.TField("modifiedItem", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField TAGS_FIELD_DESC = new org.apache.thrift.protocol.TField("tags", org.apache.thrift.protocol.TType.SET, (short)3);
  private static final org.apache.thrift.protocol.TField CUSTOM_COLUMNS_FIELD_DESC = new org.apache.thrift.protocol.TField("customColumns", org.apache.thrift.protocol.TType.LIST, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ProcessedItemResultStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ProcessedItemResultTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable Action action; // optional
  public @org.apache.thrift.annotation.Nullable ProcessedItem modifiedItem; // optional
  public @org.apache.thrift.annotation.Nullable java.util.Set<java.lang.String> tags; // optional
  public @org.apache.thrift.annotation.Nullable java.util.List<CustomColumn> customColumns; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ACTION((short)1, "action"),
    MODIFIED_ITEM((short)2, "modifiedItem"),
    TAGS((short)3, "tags"),
    CUSTOM_COLUMNS((short)4, "customColumns");

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
        case 2: // MODIFIED_ITEM
          return MODIFIED_ITEM;
        case 3: // TAGS
          return TAGS;
        case 4: // CUSTOM_COLUMNS
          return CUSTOM_COLUMNS;
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
  private static final _Fields optionals[] = {_Fields.ACTION,_Fields.MODIFIED_ITEM,_Fields.TAGS,_Fields.CUSTOM_COLUMNS};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ACTION, new org.apache.thrift.meta_data.FieldMetaData("action", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.ENUM        , "Action")));
    tmpMap.put(_Fields.MODIFIED_ITEM, new org.apache.thrift.meta_data.FieldMetaData("modifiedItem", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ProcessedItem.class)));
    tmpMap.put(_Fields.TAGS, new org.apache.thrift.meta_data.FieldMetaData("tags", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.CUSTOM_COLUMNS, new org.apache.thrift.meta_data.FieldMetaData("customColumns", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "CustomColumn"))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ProcessedItemResult.class, metaDataMap);
  }

  public ProcessedItemResult() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ProcessedItemResult(ProcessedItemResult other) {
    if (other.isSetAction()) {
      this.action = other.action;
    }
    if (other.isSetModifiedItem()) {
      this.modifiedItem = new ProcessedItem(other.modifiedItem);
    }
    if (other.isSetTags()) {
      java.util.Set<java.lang.String> __this__tags = new java.util.HashSet<java.lang.String>(other.tags);
      this.tags = __this__tags;
    }
    if (other.isSetCustomColumns()) {
      java.util.List<CustomColumn> __this__customColumns = new java.util.ArrayList<CustomColumn>(other.customColumns.size());
      for (CustomColumn other_element : other.customColumns) {
        __this__customColumns.add(new CustomColumn(other_element));
      }
      this.customColumns = __this__customColumns;
    }
  }

  public ProcessedItemResult deepCopy() {
    return new ProcessedItemResult(this);
  }

  @Override
  public void clear() {
    this.action = null;
    this.modifiedItem = null;
    this.tags = null;
    this.customColumns = null;
  }

  @org.apache.thrift.annotation.Nullable
  public Action getAction() {
    return this.action;
  }

  public ProcessedItemResult setAction(@org.apache.thrift.annotation.Nullable Action action) {
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

  @org.apache.thrift.annotation.Nullable
  public ProcessedItem getModifiedItem() {
    return this.modifiedItem;
  }

  public ProcessedItemResult setModifiedItem(@org.apache.thrift.annotation.Nullable ProcessedItem modifiedItem) {
    this.modifiedItem = modifiedItem;
    return this;
  }

  public void unsetModifiedItem() {
    this.modifiedItem = null;
  }

  /** Returns true if field modifiedItem is set (has been assigned a value) and false otherwise */
  public boolean isSetModifiedItem() {
    return this.modifiedItem != null;
  }

  public void setModifiedItemIsSet(boolean value) {
    if (!value) {
      this.modifiedItem = null;
    }
  }

  public int getTagsSize() {
    return (this.tags == null) ? 0 : this.tags.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.lang.String> getTagsIterator() {
    return (this.tags == null) ? null : this.tags.iterator();
  }

  public void addToTags(java.lang.String elem) {
    if (this.tags == null) {
      this.tags = new java.util.HashSet<java.lang.String>();
    }
    this.tags.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Set<java.lang.String> getTags() {
    return this.tags;
  }

  public ProcessedItemResult setTags(@org.apache.thrift.annotation.Nullable java.util.Set<java.lang.String> tags) {
    this.tags = tags;
    return this;
  }

  public void unsetTags() {
    this.tags = null;
  }

  /** Returns true if field tags is set (has been assigned a value) and false otherwise */
  public boolean isSetTags() {
    return this.tags != null;
  }

  public void setTagsIsSet(boolean value) {
    if (!value) {
      this.tags = null;
    }
  }

  public int getCustomColumnsSize() {
    return (this.customColumns == null) ? 0 : this.customColumns.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<CustomColumn> getCustomColumnsIterator() {
    return (this.customColumns == null) ? null : this.customColumns.iterator();
  }

  public void addToCustomColumns(CustomColumn elem) {
    if (this.customColumns == null) {
      this.customColumns = new java.util.ArrayList<CustomColumn>();
    }
    this.customColumns.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<CustomColumn> getCustomColumns() {
    return this.customColumns;
  }

  public ProcessedItemResult setCustomColumns(@org.apache.thrift.annotation.Nullable java.util.List<CustomColumn> customColumns) {
    this.customColumns = customColumns;
    return this;
  }

  public void unsetCustomColumns() {
    this.customColumns = null;
  }

  /** Returns true if field customColumns is set (has been assigned a value) and false otherwise */
  public boolean isSetCustomColumns() {
    return this.customColumns != null;
  }

  public void setCustomColumnsIsSet(boolean value) {
    if (!value) {
      this.customColumns = null;
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

    case MODIFIED_ITEM:
      if (value == null) {
        unsetModifiedItem();
      } else {
        setModifiedItem((ProcessedItem)value);
      }
      break;

    case TAGS:
      if (value == null) {
        unsetTags();
      } else {
        setTags((java.util.Set<java.lang.String>)value);
      }
      break;

    case CUSTOM_COLUMNS:
      if (value == null) {
        unsetCustomColumns();
      } else {
        setCustomColumns((java.util.List<CustomColumn>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ACTION:
      return getAction();

    case MODIFIED_ITEM:
      return getModifiedItem();

    case TAGS:
      return getTags();

    case CUSTOM_COLUMNS:
      return getCustomColumns();

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
    case MODIFIED_ITEM:
      return isSetModifiedItem();
    case TAGS:
      return isSetTags();
    case CUSTOM_COLUMNS:
      return isSetCustomColumns();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof ProcessedItemResult)
      return this.equals((ProcessedItemResult)that);
    return false;
  }

  public boolean equals(ProcessedItemResult that) {
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

    boolean this_present_modifiedItem = true && this.isSetModifiedItem();
    boolean that_present_modifiedItem = true && that.isSetModifiedItem();
    if (this_present_modifiedItem || that_present_modifiedItem) {
      if (!(this_present_modifiedItem && that_present_modifiedItem))
        return false;
      if (!this.modifiedItem.equals(that.modifiedItem))
        return false;
    }

    boolean this_present_tags = true && this.isSetTags();
    boolean that_present_tags = true && that.isSetTags();
    if (this_present_tags || that_present_tags) {
      if (!(this_present_tags && that_present_tags))
        return false;
      if (!this.tags.equals(that.tags))
        return false;
    }

    boolean this_present_customColumns = true && this.isSetCustomColumns();
    boolean that_present_customColumns = true && that.isSetCustomColumns();
    if (this_present_customColumns || that_present_customColumns) {
      if (!(this_present_customColumns && that_present_customColumns))
        return false;
      if (!this.customColumns.equals(that.customColumns))
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

    hashCode = hashCode * 8191 + ((isSetModifiedItem()) ? 131071 : 524287);
    if (isSetModifiedItem())
      hashCode = hashCode * 8191 + modifiedItem.hashCode();

    hashCode = hashCode * 8191 + ((isSetTags()) ? 131071 : 524287);
    if (isSetTags())
      hashCode = hashCode * 8191 + tags.hashCode();

    hashCode = hashCode * 8191 + ((isSetCustomColumns()) ? 131071 : 524287);
    if (isSetCustomColumns())
      hashCode = hashCode * 8191 + customColumns.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ProcessedItemResult other) {
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
    lastComparison = java.lang.Boolean.compare(isSetModifiedItem(), other.isSetModifiedItem());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetModifiedItem()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.modifiedItem, other.modifiedItem);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetTags(), other.isSetTags());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTags()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tags, other.tags);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetCustomColumns(), other.isSetCustomColumns());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCustomColumns()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.customColumns, other.customColumns);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ProcessedItemResult(");
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
    if (isSetModifiedItem()) {
      if (!first) sb.append(", ");
      sb.append("modifiedItem:");
      if (this.modifiedItem == null) {
        sb.append("null");
      } else {
        sb.append(this.modifiedItem);
      }
      first = false;
    }
    if (isSetTags()) {
      if (!first) sb.append(", ");
      sb.append("tags:");
      if (this.tags == null) {
        sb.append("null");
      } else {
        sb.append(this.tags);
      }
      first = false;
    }
    if (isSetCustomColumns()) {
      if (!first) sb.append(", ");
      sb.append("customColumns:");
      if (this.customColumns == null) {
        sb.append("null");
      } else {
        sb.append(this.customColumns);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (modifiedItem != null) {
      modifiedItem.validate();
    }
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

  private static class ProcessedItemResultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ProcessedItemResultStandardScheme getScheme() {
      return new ProcessedItemResultStandardScheme();
    }
  }

  private static class ProcessedItemResultStandardScheme extends org.apache.thrift.scheme.StandardScheme<ProcessedItemResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ProcessedItemResult struct) throws org.apache.thrift.TException {
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
          case 2: // MODIFIED_ITEM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.modifiedItem = new ProcessedItem();
              struct.modifiedItem.read(iprot);
              struct.setModifiedItemIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TAGS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set128 = iprot.readSetBegin();
                struct.tags = new java.util.HashSet<java.lang.String>(2*_set128.size);
                @org.apache.thrift.annotation.Nullable java.lang.String _elem129;
                for (int _i130 = 0; _i130 < _set128.size; ++_i130)
                {
                  _elem129 = iprot.readString();
                  struct.tags.add(_elem129);
                }
                iprot.readSetEnd();
              }
              struct.setTagsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CUSTOM_COLUMNS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list131 = iprot.readListBegin();
                struct.customColumns = new java.util.ArrayList<CustomColumn>(_list131.size);
                @org.apache.thrift.annotation.Nullable CustomColumn _elem132;
                for (int _i133 = 0; _i133 < _list131.size; ++_i133)
                {
                  _elem132 = new CustomColumn();
                  _elem132.read(iprot);
                  struct.customColumns.add(_elem132);
                }
                iprot.readListEnd();
              }
              struct.setCustomColumnsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ProcessedItemResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.action != null) {
        if (struct.isSetAction()) {
          oprot.writeFieldBegin(ACTION_FIELD_DESC);
          oprot.writeI32(struct.action.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.modifiedItem != null) {
        if (struct.isSetModifiedItem()) {
          oprot.writeFieldBegin(MODIFIED_ITEM_FIELD_DESC);
          struct.modifiedItem.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.tags != null) {
        if (struct.isSetTags()) {
          oprot.writeFieldBegin(TAGS_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, struct.tags.size()));
            for (java.lang.String _iter134 : struct.tags)
            {
              oprot.writeString(_iter134);
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.customColumns != null) {
        if (struct.isSetCustomColumns()) {
          oprot.writeFieldBegin(CUSTOM_COLUMNS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.customColumns.size()));
            for (CustomColumn _iter135 : struct.customColumns)
            {
              _iter135.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ProcessedItemResultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ProcessedItemResultTupleScheme getScheme() {
      return new ProcessedItemResultTupleScheme();
    }
  }

  private static class ProcessedItemResultTupleScheme extends org.apache.thrift.scheme.TupleScheme<ProcessedItemResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ProcessedItemResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAction()) {
        optionals.set(0);
      }
      if (struct.isSetModifiedItem()) {
        optionals.set(1);
      }
      if (struct.isSetTags()) {
        optionals.set(2);
      }
      if (struct.isSetCustomColumns()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetAction()) {
        oprot.writeI32(struct.action.getValue());
      }
      if (struct.isSetModifiedItem()) {
        struct.modifiedItem.write(oprot);
      }
      if (struct.isSetTags()) {
        {
          oprot.writeI32(struct.tags.size());
          for (java.lang.String _iter136 : struct.tags)
          {
            oprot.writeString(_iter136);
          }
        }
      }
      if (struct.isSetCustomColumns()) {
        {
          oprot.writeI32(struct.customColumns.size());
          for (CustomColumn _iter137 : struct.customColumns)
          {
            _iter137.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ProcessedItemResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.action = com.vound.intella.scripting.api.Action.findByValue(iprot.readI32());
        struct.setActionIsSet(true);
      }
      if (incoming.get(1)) {
        struct.modifiedItem = new ProcessedItem();
        struct.modifiedItem.read(iprot);
        struct.setModifiedItemIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TSet _set138 = iprot.readSetBegin(org.apache.thrift.protocol.TType.STRING);
          struct.tags = new java.util.HashSet<java.lang.String>(2*_set138.size);
          @org.apache.thrift.annotation.Nullable java.lang.String _elem139;
          for (int _i140 = 0; _i140 < _set138.size; ++_i140)
          {
            _elem139 = iprot.readString();
            struct.tags.add(_elem139);
          }
        }
        struct.setTagsIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TList _list141 = iprot.readListBegin(org.apache.thrift.protocol.TType.STRUCT);
          struct.customColumns = new java.util.ArrayList<CustomColumn>(_list141.size);
          @org.apache.thrift.annotation.Nullable CustomColumn _elem142;
          for (int _i143 = 0; _i143 < _list141.size; ++_i143)
          {
            _elem142 = new CustomColumn();
            _elem142.read(iprot);
            struct.customColumns.add(_elem142);
          }
        }
        struct.setCustomColumnsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

