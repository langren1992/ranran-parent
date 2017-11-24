package com.ranran.codegenerate.sql;

/**
 * 列信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:11
 **/
public class ColumnInfo {

    //列名
    private String name;

    //列备注
    private String comment;

    //列类型
    private String type;

    //属性名
    private String propertyName;

    //属性类型
    private String propertyType;

    //是否主键
    private Boolean primaryKey;

    //数据长度
    private Integer precision;

    //数据小数点
    private Integer scale;

    public ColumnInfo(String name, String comment, String type, String propertyName, String propertyType, Integer precision, Integer scale) {
        this.name = name;
        this.comment = comment;
        this.type = type;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.precision = precision;
        this.scale = scale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }
}
