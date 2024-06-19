package com.cocreate.service;


import com.cocreate.post.Post;
import com.cocreate.post.PostRepository;
import com.cocreate.post.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostServiceTest {

    @InjectMocks // Injects the fields annotated with @Mock into postService
    private PostService postService;

    // Dependency of PostService
    @Mock // Mockito creates a mock implementation of PostRepository
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        // We want to start/open the mocks for the PostService class
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_post() {
        // Given
        Post post = new Post(5,
                "Social media platform for dogs",
                "This application is a social media platform for dogs. Dogs should be able to follow eachother" +
                        "and like each other photos and so on and so forth...");

        // Mocking the call to the save method. Means that whenever the save method is called, return the post that
        // was created previously
        when(postRepository.save(any(Post.class))).thenReturn(post);
        // When
        Post controlPost = postService.createPost(post);

        // Then
        assertEquals(post.getTitle(), controlPost.getTitle());
        assertEquals(post.getContent(), post.getContent());

    }

    @Test
    public void should_find_post_by_id() {
        // Given
        Post post = new Post(5,
                "Social media platform for dogs",
                "This application is a social media platform for dogs. Dogs should be able to follow eachother" +
                        "and like each other photos and so on and so forth...");

        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(post));
        // When
        Optional<Post> controlPost = postService.findById(5);
        // Then
        assertEquals(controlPost.get().getTitle(), post.getTitle());
        assertEquals(controlPost.get().getContent(), post.getContent());
    }



}
