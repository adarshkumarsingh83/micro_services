package com.espark.adarsh.bean;

public class Address {

    String userId;
    String houseNumber;
    String streetNumber;
    String cityName;
    String stateName;
    String countryName;

    public Address() {
    }

    public Address(String userId, String houseNumber, String streetNumber,
                   String cityName, String stateName, String countryName) {
        this.userId = userId;
        this.houseNumber = houseNumber;
        this.streetNumber = streetNumber;
        this.cityName = cityName;
        this.stateName = stateName;
        this.countryName = countryName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
