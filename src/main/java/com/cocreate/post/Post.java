package com.cocreate.post;

import com.cocreate.comment.Comment;
import com.cocreate.developer.Developer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @Column(name = "postid") // hibernate looks for post_id per default, this is to tell it to look for postId (but it is case insensitive)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "Content cannot be empty")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL) // ALL means that whenever there is a change in the parent developer, the changes will be reflected in the post
    @JoinColumn(name = "developer_id", nullable = false ) // Specifies the foreign key column (developer_id) in the Post table that refers to the primary key of the Developer table.
    @JsonBackReference
    private Developer developer;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonManagedReference // is used to break the infinite loop that occurs when using bidirectional relationships (post - comment)
    List<Comment> comments;

    public Post(int postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }

    public Post() {

    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
}


