package com.ecommerce.micrommerce.model;

import java.util.Date;

public class Client {
    private int id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String drivingLicenseNumb;

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
