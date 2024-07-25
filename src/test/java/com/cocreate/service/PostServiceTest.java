package com.cocreate.service;

import com.cocreate.post.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    @InjectMocks // Injects the fields annotated with @Mock into postService
    private PostService postService;

    // Dependency of PostService
    @Mock // Mockito creates a mock implementation of PostRepository
    private PostRepository postRepository;
    @Mock
    private PostDTOMapper postDTOMapper;

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

        // When the save method of postRepository is called with any Post object as an argument,
        // then return the post object we've specified.
        when(postRepository.save(any(Post.class))).thenReturn(post);
        // the expected result
        /*PostDTO expectedPostDTO = postDTOMapper.apply(post);
        // When
        PostDTO actualPostDTO = postService.createPost(post);
        // Then
        assertEquals(actualPostDTO, expectedPostDTO);*/
        //assertEquals(post.getContent(), controlPost.getContent());

        verify(postRepository).save(post);
    }

    @Test
    public void should_find_post_by_id() {
        // Given
        Post post = new Post(5,
                "Social media platform for dogs",
                "This application is a social media platform for dogs. Dogs should be able to follow eachother" +
                        "and like each other photos and so on and so forth...");

        // We are returning an Optional<Post> because that is indeed what the postrepository.findbyid
        // method is returning. Since that's the method we are mocking, we should also use the same return type.
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(post));
        PostDTO expectedPostDTO = postService.findById(5);
        // When
       /* PostDTO actualPostDTO = postDTOMapper.apply(post);
        // Then
        assertEquals(actualPostDTO, expectedPostDTO);*/
        verify(postRepository).findById(5);
    }

    @Test
    public void should_update_a_post() {

    }



}
