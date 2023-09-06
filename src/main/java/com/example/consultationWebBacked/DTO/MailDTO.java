package com.example.consultationWebBacked.DTO;

public class MailDTO {
    private String id;
    private String tomail;
    private String message;
    private String subject;

    public MailDTO() {
    }

    public MailDTO(String id, String tomail, String message, String subject) {
        this.id = id;
        this.tomail = tomail;
        this.message = message;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
