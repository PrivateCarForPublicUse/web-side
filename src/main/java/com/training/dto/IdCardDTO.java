package com.training.dto;

public class IdCardDTO {
    private String name;
    private String IdNumber;
    private String UseEndTime;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getUseEndTime() {
        return UseEndTime;
    }

    public void setUseEndTime(String useEndTime) {
        UseEndTime = useEndTime;
    }
}
