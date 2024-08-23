package com.cocreate.comment;

import com.cocreate.exceptions.ResourceNotFoundException;
import com.cocreate.post.Post;
import com.cocreate.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentDTOMapper commentDTOMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, CommentDTOMapper commentDTOMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentDTOMapper = commentDTOMapper;
    }

    // Create a comment
    public CommentDTO createComment(int postId, Comment comment) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) {
            Comment newComment = new Comment();
            newComment.setContent(comment.getContent());
            newComment.setPost(post.get());
            commentRepository.save(newComment);
            return commentDTOMapper.mapToDTO(comment);
        } else {
            throw new ResourceNotFoundException("The post doesn't exist.");
        }
    }

    // Find all comments of a specific post
    public List<CommentDTO> findCommentsOfPost(int postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isPresent()) {
            Post post = postOptional.get();
            return post.getComments()
                    .stream() // Create a stream from the List<Comment>
                    // shorthand for lambda expression: comment -> commentDTOMapper.mapToDTO(comment)
                    // Works because the mapToDTO method conforms to the signature requirements of the generic apply() method of the Function interface
                    .map(commentDTOMapper::mapToDTO)
                    .collect(Collectors.toList()); // Convert the stream into a list of CommentDTOs
        } else {
            throw new ResourceNotFoundException("The post couldn't be found");
        }
    }


}
