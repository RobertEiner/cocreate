package com.cocreate.developer;

import com.cocreate.post.Post;

import java.util.List;

// According to amigoscode, a DTO should be as simple as possible with no logic
public record DeveloperDTO(
        String userName,
        String emailAddress,
        String preferredLanguages,
        List<Post> posts
) {


}
