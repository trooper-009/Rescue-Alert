package com.example.rescuealert;

public class LocationSend {
    private double Longitude;
    private double latitude;

    public LocationSend(double longitude, double latitude) {
        this.Longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
