package com.ecommerce.micrommerce.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

// @JsonIgnoreProperties ignore une liste de propriété
//@JsonIgnoreProperties(value = {"drivingLicenseNumb", "id"})

// @JsonFilter declare un filtre et on declare un nom
@JsonFilter("monFiltreDynamique")
//@Entity
public class Client {
    //@Id
    private int id;
    //@Size(min = 3, max = 15)
    private String firstname;
    //@Size(min = 3, max = 15)
    private String lastname;
    private Date birthdate;

    // information que nous ne souhaitons pas exposer
    //@JsonIgnore ignore la propriété
    //@JsonIgnore
    private String drivingLicenseNumb;

    // constructeur par défaut
    public Client(){

    }

    // constructeur pour nos tests
    public Client(int id, String firstname, String lastname, Date birthdate, String drivingLicenseNumb) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.drivingLicenseNumb = drivingLicenseNumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getDrivingLicenseNumb() {
        return drivingLicenseNumb;
    }

    public void setDrivingLicenseNumb(String drivingLicenseNumb) {
        this.drivingLicenseNumb = drivingLicenseNumb;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                ", drivingLicenseNumb='" + drivingLicenseNumb + '\'' +
                '}';
    }

}
