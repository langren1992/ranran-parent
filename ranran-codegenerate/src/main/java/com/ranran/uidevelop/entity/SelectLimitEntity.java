package com.ranran.uidevelop.entity;

public class SelectLimitEntity {

    private String offset;

    private String rowCount;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public String toString() {
        return "SelectLimitEntity{" +
                "offset='" + offset + '\'' +
                ", rowCount='" + rowCount + '\'' +
                '}';
    }
}
