package com.training.dto;

public class PointLatDTO {
    private String Longitude;
    private String Latitude;

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public PointLatDTO(String longitude, String latitude) {
        Longitude = longitude;
        Latitude = latitude;
    }
}
