package com.cocreate.service;

import com.cocreate.exceptions.ResourceNotFoundException;
import com.cocreate.post.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    @InjectMocks // Injects the fields annotated with @Mock into postService
    private PostService postService;

    // Dependencies of PostService
    @Mock // Mockito creates a mock implementation of PostRepository
    private PostRepository postRepository;
    @Mock
    private final PostDTOMapper postDTOMapper = new PostDTOMapper(new ModelMapper());

    @BeforeEach
    void setUp() {
        // We want to start/open the mocks for the PostService class
        MockitoAnnotations.openMocks(this);
    }

    // ------------ CREATE -----------------------
    @Test
    public void should_successfully_save_a_post() {
        // Given
        Post post = new Post(5,
                "Social media platform for dogs",
                "This application is a social media platform for dogs. Dogs should be able to follow eachother" +
                        "and like each other photos and so on and so forth...");
        PostDTO postDTO = new PostDTO(post.getContent(), post.getTitle(), post.getDeveloper(), post.getComments());
        // When the save method of postRepository is called with any Post object as an argument,
        // then return the post object we've specified.
        when(postRepository.save(any(Post.class))).thenReturn(post);
        when(postDTOMapper.mapToDTO(any(Post.class))).thenReturn(postDTO);
        // the expected result
        PostDTO expectedPostDTO = postDTOMapper.mapToDTO(post);
        // When
        PostDTO actualPostDTO = postService.createPost(post);
        // Then
        assertEquals(actualPostDTO, expectedPostDTO);
        verify(postRepository).save(post);
    }

    // --------------- GET -------------------------
    @Test
    public void should_FindPostById_When_PostExists() {
        // Given
        Post post = new Post(5,
                "Social media platform for dogs",
                "This application is a social media platform for dogs. Dogs should be able to follow eachother" +
                        "and like each other photos and so on and so forth...");
        PostDTO postDTO = new PostDTO(post.getContent(), post.getTitle(), post.getDeveloper(), post.getComments());


        // We are returning an Optional<Post> because that is indeed what the postrepository.findbyid
        // method is returning. Since that's the method we are mocking, we should also use the same return type.
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(post));
        when(postDTOMapper.mapToDTO(any(Post.class))).thenReturn(postDTO);
        PostDTO expectedPostDTO = postService.findById(5);
        PostDTO actualPostDTO = postDTOMapper.mapToDTO(post);
        // Then
        assertEquals(actualPostDTO, expectedPostDTO);
        verify(postRepository).findById(5);
    }

    @Test
    public void should_ThrowExceptionInFindPostById_When_PostDoesntExist () {
        int postId = 5;

        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
              postService.findById(postId);
        });
    }

     // --------------- UPDATE --------------------

    @Test
    public void should_UpdatePostSuccessfully_When_PostExists() {
        // Given
        int postId = 5;
        Post existingPost = new Post(postId,
                "Social media platform for dogs",
                "Content of existing post");
        String newTitle = "New title";
        String newContent = "New content";
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(existingPost));
        //Then
        postService.updatePost(postId, newTitle, newContent); // the postRepository.save method will not do anything by default.
        assertEquals(newTitle, existingPost.getTitle());
        assertEquals(newContent, existingPost.getContent());
        verify(postRepository).findById(postId);
        verify(postRepository).save(existingPost);
    }

    @Test
    public void should_ThrowExceptionOnUpdate_When_PostDoesntExist() {
        // Given
        int postId = 5;
        String newTitle = "New title";
        String newContent = "New content";

        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
            postService.updatePost(postId, newTitle, newContent);
        });
        verify(postRepository, never()).save(any(Post.class)); // verifies so that the save method was never called
    }

    // ------------ DELETE --------------------------
    @Test
    public void should_DeletePost_When_PostExists() {
        int postId = 5;
        Post postToDelete = new Post(postId, "Title", "Content");

        //When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(postToDelete));
        //Then
        postService.deletePost(postId);
        verify(postRepository).findById(postId);
        verify(postRepository).delete(postToDelete);
    }

    @Test
    public void should_ThrowExceptionOnDelete_When_PostDoesntExist() {
        // Given
        int postId = 5;
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
           postService.deletePost(postId);
        });
        verify(postRepository, never()).delete(any(Post.class));
    }


}
