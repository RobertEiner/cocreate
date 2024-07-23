package com.cocreate.comment;

import com.cocreate.exceptions.ResourceNotFoundException;
import com.cocreate.post.Post;
import com.cocreate.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public void createComment(int postId, Comment comment) {
        Optional<Post> post =  postRepository.findById(postId);
        if(post.isPresent()) {
            Comment newComment = new Comment();
            newComment.setContent(comment.getContent());
            newComment.setPost(post.get());
            commentRepository.save(newComment);
        } else {
            throw new ResourceNotFoundException("The post doesn't exist");
        }
    }

    public List<Comment> findCommentsOfPost(int postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isPresent()) {
            Post post = postOptional.get();
            return post.getComments();
        } else {
            throw new ResourceNotFoundException("The post couldn't be found");
        }
    }


}
