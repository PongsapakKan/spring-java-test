package com.userinfo.models.api.requests;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegistration {
    @NotNull
    @Length(min = 5)
    private String username;
    @NotNull
    @Length(min = 5)
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phoneNo;
    @Min(value = 15000, message = "salary must greater or equal than 15000")
    private int salary;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
