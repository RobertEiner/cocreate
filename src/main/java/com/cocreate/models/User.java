package com.cocreate.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

// @entity marks the class as a JPA entity. These entities are mapped to tables in relational databases.
@Entity
@Table(name="user") // if the names of the entity class and the db table does not match, use this to clarify it.
public class User {

    @Id // indicating that the member field below is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // means that we are auto-incrementing the id
    private int userId;
    @NotEmpty(message = "User name cannot be empty!")                   // part of the jakarta validation dependency
    @Column(unique = true)      // user name needs to be unique
    private String userName;
    @NotEmpty(message = "Email cannot be empty!")
    @Column(unique = true)      // email needs to be unique
    private String emailAddress;

    @NotEmpty(message = "A preferred language must be added")
    private String preferredLanguage;


    public User(String userName, String emailAddress, String preferredLanguages) {
        if(userName.isEmpty()) throw new IllegalArgumentException("Username cannot be empty.");
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.preferredLanguage= preferredLanguages;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguages(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
}
