package com.jcCoder.springboottut.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long departmentId;

    @NotBlank(message = "Enter the Department name")
    private String departmentName;

    @NotNull(message = "Address Can't be null")
    private String departmentAddress;

    private String departmentCode;

    @Email(message = "Invalid Email Address , Please Enter a valid mail")
    private String emailAddress;

    public Department() {

    }


    public Department(Long departmentId, String departmentName, String departmentAddress, String emailAddress) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentAddress = departmentAddress;
        this.emailAddress = emailAddress;
    }

    public Long getDepartmentId() {
        return departmentId;
    }


    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    @Override
    public String toString() {
        return "Department{" + "departmentId=" + departmentId + ", departmentName='" + departmentName + '\'' + ", departmentAddress='" + departmentAddress + '\'' + ", departmentCode='" + departmentCode + '\'' + ", emailAddress='" + emailAddress + '\'' + '}';
    }
}
