package com.cocreate.developer;

import com.cocreate.post.Post;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Developer {

    @Id // indicating that the member field below is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // means that we are auto-incrementing the id
    private int developerId;
    @NotEmpty(message = "User name cannot be empty!")                   // part of the jakarta validation dependency
    @Column(unique = true)      // user name needs to be unique
    private String userName;
    @NotEmpty(message = "Email cannot be empty!")
    @Column(unique = true)      // email needs to be unique
    private String emailAddress;

    @NotEmpty(message = "A preferred language must be added")
    private String preferredLanguage;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.REMOVE) // mappedBy should be set to the name that the developer object has in the post entity
    @JsonManagedReference
    private List<Post> posts;

    public Developer(String userName, String emailAddress, String preferredLanguages) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.preferredLanguage= preferredLanguages;
    }

    public Developer() {

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

    public void setUserId(int developerId) {
        this.developerId = developerId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}

