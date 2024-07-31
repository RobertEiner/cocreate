package com.cocreate.service;

import com.cocreate.comment.*;
import com.cocreate.exceptions.ResourceNotFoundException;
import com.cocreate.post.Post;
import com.cocreate.post.PostRepository;
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

public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    PostRepository postRepository;
    @Mock
    CommentDTOMapper commentDTOMapper = new CommentDTOMapper(new ModelMapper());
    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_CreateComment_WhenPostExists() {
        // Given
        int postId = 5;
        int commentId = 6;
        Post existingPost = new Post(postId, "Title", "content");
        Comment comment = new Comment(commentId, "Comment content", existingPost);
        CommentDTO commentDTO = new CommentDTO(comment.getContent());
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(existingPost));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        when(commentDTOMapper.mapToDTO(any(Comment.class))).thenReturn(commentDTO);
        CommentDTO expectedCommentDTO = commentDTOMapper.mapToDTO(comment);
        CommentDTO actualCommentDTO = commentService.createComment(postId, comment);
        // Then
        assertEquals(expectedCommentDTO, actualCommentDTO);
        verify(postRepository).findById(any(Integer.class));
        verify(commentRepository).save(any(Comment.class));
        verify(commentDTOMapper, times(2)).mapToDTO(any(Comment.class));
    }

    @Test
    public void should_ThrowException_When_PostNotFound() {
        // Given
        int postId = 5;
        int commentId = 6;
        Post post = new Post();
        Comment comment = new Comment(commentId, "Content", post);
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
           commentService.createComment(postId, comment);
        });
        verify(postRepository).findById(any(Integer.class));
        verify(commentRepository, never()).save(any(Comment.class));
        verify(commentDTOMapper, never()).mapToDTO(any(Comment.class));

    }





}
