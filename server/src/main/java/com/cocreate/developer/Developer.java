package com.cocreate.developer;

import com.cocreate.comment.Comment;
import com.cocreate.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Developer {

    @Id // indicating that the member field below is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // means that we are auto-incrementing the id
    private int developerId;

    @NotEmpty(message = "User name cannot be empty!")                   // part of the jakarta validation dependency
    @Column(unique = true)      // user name needs to be unique
    private String userName;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "Email cannot be empty!")
    @Column(unique = true)      // email needs to be unique
    private String emailAddress;

//    @NotEmpty(message = "A preferred language must be added")
//    private String preferredLanguage;

    // mappedBy should be set to the name that the developer object has in the post entity
    // ALL means that whenever there is a change in the parent developer, the changes will be reflected in the post
    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL) // CascadeType.ALL should be on the OneToMAny side of the bidirectional relationship
    //@JsonManagedReference
    @JsonIgnoreProperties( "developer")
    private List<Post> posts;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL)
    // When a comment in the list of comments is serialized, the developer field of that comment will not be serialized.
    // This is to avoid infinite recursion problem
    @JsonIgnoreProperties("developer") // developer denotes the name of the Developer field in the comment class
    private List<Comment> comments;

    public Developer(String userName, String emailAddress, String preferredLanguages) {
        this.userName = userName;
        this.emailAddress = emailAddress;
//        this.preferredLanguage= preferredLanguages;
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

//    public String getPreferredLanguage() {
//        return preferredLanguage;
//    }

//    public void setPreferredLanguages(String preferredLanguage) {
//        this.preferredLanguage = preferredLanguage;
//    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }
    public int getDeveloperId() {
        return this.developerId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getPassword() {
        return this.password;
    }
}

