package com.espark.adarsh.bean;

public class ApiBean {

    String id;
    Address address;
    User user;

    public ApiBean() {
    }

    public ApiBean(String id, Address address, User user) {
        this.id = id;
        this.address = address;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
