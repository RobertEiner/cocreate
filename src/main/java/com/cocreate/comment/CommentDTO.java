package com.cocreate.comment;

import com.cocreate.post.Post;
import lombok.Getter;
import lombok.Setter;

// Getters and Setters needed so that modelMapper works correctly
@Getter
@Setter
public class CommentDTO {
    private String content;

}
