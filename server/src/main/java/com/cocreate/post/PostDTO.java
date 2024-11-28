package com.cocreate.post;

import com.cocreate.comment.Comment;
import com.cocreate.developer.Developer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    Integer postId;
    String content;
    String title;
    Developer developer;
    List<Comment> comments;
    String createdAt;

}
