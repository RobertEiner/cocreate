package com.cocreate.comment;


import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Content cannot be empty")
    private String content;

}
