package com.example.consultationWebBacked.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fName;
    private String mName;
    private String lNamed;
    private String address;
    private String age;
    private String dob;
    private String sex;
    private String date;
    private String time;
    private String email;
    private String phone;
    public Appointment() {
    }

    public Appointment(int id, String fName, String mName, String lNamed, String address, String age, String dob, String sex, String date, String time, String email, String phone) {
        this.id = id;
        this.fName = fName;
        this.mName = mName;
        this.lNamed = lNamed;
        this.address = address;
        this.age = age;
        this.dob = dob;
        this.sex = sex;
        this.date = date;
        this.time = time;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlNamed() {
        return lNamed;
    }

    public void setlNamed(String lNamed) {
        this.lNamed = lNamed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
