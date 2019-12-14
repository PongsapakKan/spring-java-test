package com.userinfo.models.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMDD");
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String referenceCode;

    @Column(nullable = false)
    private String phoneNo;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int salary;

    @Column(nullable = false)
    private Date registerDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType.Classification memberType;

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public int getSalary() {
        return salary;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public String getMemberType() {
        return memberType.toString();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setMemberType(MemberType.Classification memberType) {
        this.memberType = memberType;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
