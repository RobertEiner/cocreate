package com.cocreate.post;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PostDTOMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public PostDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PostDTO mapToDTO(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }
}
