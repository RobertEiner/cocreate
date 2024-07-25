package com.cocreate.post;

import com.cocreate.comment.Comment;
import com.cocreate.developer.Developer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDTO {
    String content;
    String title;
    Developer developer;
    List<Comment> comments;
    
}
