package com.example.consultationWebBacked.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mail")
public class Mails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tomail;
    private String message;
    private String subject;

    public Mails() {
    }

    public Mails(int id, String tomail, String message, String subject) {
        this.id = id;
        this.tomail = tomail;
        this.message = message;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTomail() {
        return tomail;
    }

    public void setTomail(String tomail) {
        this.tomail = tomail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
