package com.cocreate.comment;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentDTOMapper  {
   private final ModelMapper modelmapper;

    @Autowired
    public CommentDTOMapper(ModelMapper modelmapper) {
        this.modelmapper = modelmapper;
    }

    public CommentDTO mapToDTO(Comment comment) {
        return modelmapper.map(comment, CommentDTO.class);
    }
}
