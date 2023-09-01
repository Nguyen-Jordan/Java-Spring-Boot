package com.ecommerce.micrommerce.model;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

// @JsonIgnoreProperties ignore une liste de propriété
//@JsonIgnoreProperties(value = {"drivingLicenseNumb", "id"})

// @JsonFilter declare un filtre et on declare un nom
//@JsonFilter("monFiltreDynamique")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    //@Size(min = 3, max = 15)
    private String firstName;
    //@Size(min = 3, max = 15)
    private String lastName;
    private Date birthdate;

    // information que nous ne souhaitons pas exposer
    //@JsonIgnore ignore la propriété
    //@JsonIgnore
    private String licenseId;

    // constructeur par défaut
    public Customer(){

    }

    // constructeur pour nos tests
    public Customer(int id, String firstName, String lastName, Date birthdate, String licenseId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.licenseId = licenseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String drivingLicenseNumb) {
        this.licenseId = drivingLicenseNumb;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", drivingLicenseNumb='" + licenseId + '\'' +
                '}';
    }

}
