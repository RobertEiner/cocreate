package com.cocreate.post;

import com.cocreate.comment.Comment;
import com.cocreate.developer.Developer;

import java.util.List;

public record PostDTO(
        String content,
        String title,
        Developer developer,
        List<Comment> comments
) {




}
