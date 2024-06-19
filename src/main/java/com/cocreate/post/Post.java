package com.cocreate.post;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Post {

    @Id
    @Column(name = "postid") // hibernate looks for post_id per default, this is to tell it to look for postId (but it is case insensitive)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "Content cannot be empty")
    private String content;

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
}

