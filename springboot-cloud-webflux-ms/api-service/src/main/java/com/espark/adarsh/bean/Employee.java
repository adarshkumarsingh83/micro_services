package com.espark.adarsh.bean;

public class Employee implements BeanInf {

    private Long id;
    private String firstName;
    private String lastName;
    private String career;
    private Type type = Type.EMPLOYEE;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String career) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
    }

    public Employee(Long id, String firstName, String lastName, String career) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    @Override
    public Type getType() {
        return this.type;
    }
}

