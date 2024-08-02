package com.cocreate.developer;

import com.cocreate.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// According to amigoscode, a DTO should be as simple as possible with no logic

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDTO {
        String userName;
        String emailAddress;
        String preferredLanguages;
        List<Post> posts;
}
