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

import java.util.ArrayList;
import java.util.List;
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

    // -------------- CREATE ------------------
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

    // -------------- GET ------------------
    @Test
    public void should_FindAllComments_When_PostExists() {
        // Given
        int postId = 5, commentId1 = 6, commentId2 = 7, commentId3 = 8;
        Post post = new Post(postId, "Title", "Content");
        Comment comment1 = new Comment(commentId1, "Content1", post);
        Comment comment2 = new Comment(commentId2, "Content2", post);
        Comment comment3 = new Comment(commentId3, "Content3", post);
        post.setComments(List.of(comment1, comment2, comment3));
        CommentDTO commentDTO1 = new CommentDTO(comment1.getContent());
        CommentDTO commentDTO2 = new CommentDTO(comment2.getContent());
        CommentDTO commentDTO3 = new CommentDTO(comment3.getContent());
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(post));
        // when(commentDTOMapper.mapToDTO(comment1)).thenReturn(commentDTO1);
        // when(commentDTOMapper.mapToDTO(comment2)).thenReturn(commentDTO2);
        //when(commentDTOMapper.mapToDTO(comment3)).thenReturn(commentDTO3);
        when(commentDTOMapper.mapToDTO(any(Comment.class))).thenAnswer(invocation -> {
            Comment comment = invocation.getArgument(0);
            return new CommentDTO(comment.getContent());
        });

        List<CommentDTO> commentDTOs = commentService.findCommentsOfPost(postId);
        // Then
        assertEquals(commentDTO1.getContent(), commentDTOs.get(0).getContent());
        assertEquals(commentDTO2.getContent(), commentDTOs.get(1).getContent());
        assertEquals(commentDTO3.getContent(), commentDTOs.get(2).getContent());
        assertEquals(3, commentDTOs.size());
        verify(postRepository).findById(any(Integer.class));
        verify(commentDTOMapper, times(3)).mapToDTO(any(Comment.class));
    }

    @Test
    public void should_ThrowExceptionWhenFetchingComments_When_PostNotFound() {
        // Given
        int postId = 5;
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
           commentService.findCommentsOfPost(postId);
        });
        verify(commentDTOMapper, never()).mapToDTO(any(Comment.class));
    }

    @Test
    public void should_ReturnEmptyList_When_PostHasNoComments() {
        // Given
        int postId = 5;
        Post post = new Post(postId, "Title", "Content");
        List<Comment> comments = new ArrayList<>();
        post.setComments(comments);
        List<CommentDTO> expected = new ArrayList<>();

        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(post));
        when(commentDTOMapper.mapToDTO(any(Comment.class))).thenReturn(null);

        List<CommentDTO> actual = commentService.findCommentsOfPost(postId);
        // Then
        assertEquals(actual, expected);
        // the stream is empty because there are no comments, therefore,
        // mapToDTO is never called, therefore we verify with never()
        verify(commentDTOMapper, never()).mapToDTO(any(Comment.class));
    }




}
