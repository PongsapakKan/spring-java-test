package com.userinfo.models.api.requests;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegistration {
    @NotNull @Length(min = 5)
    @Pattern(regexp = "[A-Za-z0-9]*", message = "Username cannot have special character.")
    private String username;
    @NotNull @Length(min = 5)
    @Pattern(regexp = "[A-Za-z0-9]*", message = "Password cannot have special character.")
    private String password;

    @NotNull @Length(min = 3)
    @Pattern(regexp = "[A-Za-z]*", message = "First name must be alphabet.")
    private String firstName;

    @NotNull @Length(min = 3)
    @Pattern(regexp = "[A-Za-z]*", message = "Last name must be alphabet.")
    private String lastName;

    @NotNull
    private String address;

    @NotNull
    @Pattern(regexp = "[0-9]{4,}", message = "Phone number must be number and have at least 4 digits.")
    private String phoneNo;
    @NotNull
    @Min(value = 15000, message = "Salary must greater or equal than 15000.")
    private int salary;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo.trim();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
