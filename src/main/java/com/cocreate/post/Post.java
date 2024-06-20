package com.cocreate.post;

import com.cocreate.developer.Developer;
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

    @ManyToOne(cascade = CascadeType.ALL) // ALL means that whenever there is a change in the parent developer, the changes will be reflected in the post
    @JoinColumn(name = "developer_id", nullable = false ) // Specifies the foreign key column (developer_id) in the Post table that refers to the primary key of the Developer table.
    private Developer developer;

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

/*
* create table post
(
    post_id      int auto_increment
        primary key,
    title        varchar(50)   not null,
    content      varchar(1000) not null,
    developer_id int           null,
    constraint post_ibfk_1
        foreign key (developer_id) references developer (developer_id)
            on update cascade on delete cascade
);

create index developer_id
    on post (developer_id);
*/

