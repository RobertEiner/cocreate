package com.cocreate.post;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
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



}

