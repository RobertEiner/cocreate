package com.cocreate.service;

import com.cocreate.comment.*;
import com.cocreate.developer.Developer;
import com.cocreate.developer.DeveloperRepository;
import com.cocreate.exceptions.ResourceNotFoundException;
import com.cocreate.post.Post;
import com.cocreate.post.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
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
    private PostRepository postRepository;
    @Mock
    private DeveloperRepository developerRepository;
    @Mock
    private CommentDTOMapper commentDTOMapper = new CommentDTOMapper(new ModelMapper());
    @InjectMocks
    private CommentService commentService;

    private int postId, commentId;
    Developer dev;
    private LocalDateTime ldt = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postId = 1;
        commentId = 2;
        dev = new Developer();
        dev.setDeveloperId(1);
    }

    // -------------- CREATE ------------------
    @Test
    public void should_CreateComment_WhenPostExists() {
        // Given

        Post existingPost = new Post(postId, "Title", "content");
        Comment comment = new Comment(commentId, "Comment content", existingPost, dev, ldt);
        CommentDTO commentDTO = new CommentDTO(comment.getContent(), ldt.toString());
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(existingPost));
        when(developerRepository.findById(any(Integer.class))).thenReturn(Optional.of(dev));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        when(commentDTOMapper.mapToDTO(any(Comment.class))).thenReturn(commentDTO);

        CommentDTO expectedCommentDTO = commentDTOMapper.mapToDTO(comment);
        CommentDTO actualCommentDTO = commentService.createComment(postId, commentDTO, dev.getDeveloperId());
        // Then
        assertEquals(expectedCommentDTO, actualCommentDTO);
        verify(postRepository).findById(any(Integer.class));
        verify(commentRepository).save(any(Comment.class));
        verify(commentDTOMapper).mapToDTO(any(Comment.class));
    }

    @Test
    public void should_ThrowExceptionOnCreateComment_When_PostNotFound() {
        // Given
        Post post = new Post();
        CommentDTO commentDTO = new CommentDTO("Content", ldt.toString());
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(developerRepository.findById(any(Integer.class))).thenReturn(Optional.of(dev));

        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
           commentService.createComment(postId, commentDTO, dev.getDeveloperId());
        });
        verify(postRepository).findById(any(Integer.class));
        verify(commentRepository, never()).save(any(Comment.class));
        verify(commentDTOMapper, never()).mapToDTO(any(Comment.class));

    }

    // -------------- GET ------------------
    @Test
    public void should_FindAllCommentsAndReturnListOfCommentDTOs_When_PostExists() {
        // Given
        int commentId2 = 7, commentId3 = 8;
        Post post = new Post(postId, "Title", "Content");
        Comment comment1 = new Comment(commentId, "Content1", post, dev, ldt);
        Comment comment2 = new Comment(commentId2, "Content2", post, dev, ldt);
        Comment comment3 = new Comment(commentId3, "Content3", post, dev, ldt);
        post.setComments(List.of(comment1, comment2, comment3));
        CommentDTO commentDTO1 = new CommentDTO(comment1.getContent(), ldt.toString());
        CommentDTO commentDTO2 = new CommentDTO(comment2.getContent(), ldt.toString());
        CommentDTO commentDTO3 = new CommentDTO(comment3.getContent(), ldt.toString());
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(post));
        // Using thenAnswer here for less code repetition. Comparing the content in the assertions below,
        // and not the objects like in developerService test
        when(commentDTOMapper.mapToDTO(any(Comment.class))).thenAnswer(invocation -> {
            Comment comment = invocation.getArgument(0);
            return new CommentDTO(comment.getContent(), ldt.toString());
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
    public void should_ThrowExceptionOnFindAllComments_When_PostNotFound() {
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
           commentService.findCommentsOfPost(postId);
        });
        verify(commentDTOMapper, never()).mapToDTO(any(Comment.class));
    }

    @Test
    public void should_ReturnEmptyListOnFindAllComments_When_PostHasNoComments() {
        // Given
        Post post = new Post(postId, "Title", "Content");
        List<Comment> comments = new ArrayList<>();
        post.setComments(comments);
        List<CommentDTO> expected = new ArrayList<>();
        // When
        when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(post));
        // Not necessary to mock mapToDTO because it is never called since the stream will be empty.
        //when(commentDTOMapper.mapToDTO(any(Comment.class))).thenReturn(null);

        List<CommentDTO> actual = commentService.findCommentsOfPost(postId);
        // Then
        assertEquals(actual, expected);
        // the stream is empty because there are no comments, therefore,
        // mapToDTO is never called, therefore we verify with never()
        verify(commentDTOMapper, never()).mapToDTO(any(Comment.class));
    }




}
