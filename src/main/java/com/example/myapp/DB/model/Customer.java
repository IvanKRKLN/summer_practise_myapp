package com.example.myapp.DB.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerCode;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerInn;

    private String customerKpp;
    private String customerLegalAddress;
    private String customerPostalAddress;
    private String customerEmail;
    private String customerCodeMain;
    private Boolean isOrganization;
    private Boolean isPerson;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lot> lots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerInn() {
        return customerInn;
    }

    public void setCustomerInn(String customerInn) {
        this.customerInn = customerInn;
    }

    public String getCustomerKpp() {
        return customerKpp;
    }

    public void setCustomerKpp(String customerKpp) {
        this.customerKpp = customerKpp;
    }

    public String getCustomerLegalAddress() {
        return customerLegalAddress;
    }

    public void setCustomerLegalAddress(String customerLegalAddress) {
        this.customerLegalAddress = customerLegalAddress;
    }

    public String getCustomerPostalAddress() {
        return customerPostalAddress;
    }

    public void setCustomerPostalAddress(String customerPostalAddress) {
        this.customerPostalAddress = customerPostalAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerCodeMain() {
        return customerCodeMain;
    }

    public void setCustomerCodeMain(String customerCodeMain) {
        this.customerCodeMain = customerCodeMain;
    }

    public Boolean getOrganization() {
        return isOrganization;
    }

    public void setOrganization(Boolean organization) {
        isOrganization = organization;
    }

    public Boolean getPerson() {
        return isPerson;
    }

    public void setPerson(Boolean person) {
        isPerson = person;
    }

}
