package com.example.DndProject.Models.DC;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActionDc {
    private int id;
    @JsonProperty("dc_type")
    private DcType dcType;
    @JsonProperty("dc_value")
    private int dcValue;
    @JsonProperty("success_type")
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
