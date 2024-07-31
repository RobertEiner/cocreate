package com.cocreate.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Getters and Setters needed so that modelMapper works correctly
@Getter
@Setter
@AllArgsConstructor
public class CommentDTO {
    private String content;

}
