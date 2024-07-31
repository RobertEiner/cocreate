package com.cocreate.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Getters and Setters needed so that modelMapper works correctly
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // Necessary for the streams.map method to work
public class CommentDTO {
    private String content;

}
