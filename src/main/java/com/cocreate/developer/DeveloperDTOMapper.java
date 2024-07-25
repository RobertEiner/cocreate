package com.cocreate.developer;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DeveloperDTOMapper implements Function<Developer, DeveloperDTO> {
// When a class implements the Function interface, it says that it wants to convert something from one class
// to something else. In this case a developer to a developerDTO.
// Developer is the input type, developerDTO is the output type.

    @Override
    public DeveloperDTO apply(Developer developer) {
        return new DeveloperDTO(
                developer.getUserName(),
                developer.getEmailAddress(),
                developer.getPreferredLanguage(),
                developer.getPosts()
        );
    }




}
