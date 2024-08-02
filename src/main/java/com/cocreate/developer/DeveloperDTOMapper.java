package com.cocreate.developer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperDTOMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public DeveloperDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DeveloperDTO mapToDTO (Developer developer) {
        return modelMapper.map(developer, DeveloperDTO.class);
    }




}
