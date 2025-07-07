package com.example.DndProject.Models.DC;

public class ActionDc {
    private int id;
    private DcType dcType;
    private int dcValue;
    private String successType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DcType getDcType() {
        return dcType;
    }

    public void setDcType(DcType dcType) {
        this.dcType = dcType;
    }

    public int getDcValue() {
        return dcValue;
    }

    public void setDcValue(int dcValue) {
        this.dcValue = dcValue;
    }

    public String getSuccessType() {
        return successType;
    }

    public void setSuccessType(String successType) {
        this.successType = successType;
    }
}
