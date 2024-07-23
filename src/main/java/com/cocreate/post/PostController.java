package com.cocreate.post;

import com.cocreate.comment.Comment;
import com.cocreate.comment.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void createPost(@RequestBody @Valid Post newPost) {  // @Valid will trigger the validations when creating a user, as you have set in User.java
        postService.createPost(newPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> findPostById(@PathVariable int id) {
        Optional<Post> post = postService.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(post);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Optional<Post>> updatePost(@PathVariable int id, @RequestBody Post post) { // @RequestBody will map the Http body to a user object in java
        postService.updatePost(id, post.getTitle(), post.getContent());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Post>> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Create comment
    @PostMapping("/{id}/comments")
    public ResponseEntity<Optional<String>> createComment(@PathVariable int id, @RequestBody Comment comment) {
        commentService.createComment(id, comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> findAllComments(@PathVariable int id) {
        List<Comment> comments = commentService.findCommentsOfPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }




}

