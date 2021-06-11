package com.example.animeworld;

public class ContactPerson {
    private String contactId, Email, subject, message;

    //parameterized construction
    public ContactPerson(String contactId, String email, String subject, String message) {
        this.contactId = contactId;
        Email = email;
        this.subject = subject;
        this.message = message;
    }

    //default constructor
    public ContactPerson() {
    }

    //getter to retrive data
    public String getContactId() {
        return contactId;
    }

    public String getEmail() {
        return Email;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
