package com.cocreate.comment;

import com.cocreate.developer.Developer;
import com.cocreate.post.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @NotEmpty(message = "The comment needs content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"developer", "comments"})
    private Post post;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    @JsonIgnoreProperties({"comments", "posts"})
    private Developer developer;

    // 1. lägg till created at här
    // 2. lägg till created at i DTO
    // 3. lägg till created at i frontend comment
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}


