package com.cocreate.post;

import com.cocreate.exceptions.PostNotFoundException;
import com.cocreate.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post newPost) {
        return postRepository.save(newPost);
    }

    public Optional<Post> findById(int id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            return post;
        } else {
            throw new PostNotFoundException("The post doesn't exist.");
        }
    }

    public void updatePost(int id, String title, String content) {
        Optional<Post> post = findById(id);
        if(post.isPresent()) {
            Post existingPost = post.get();
            existingPost.setTitle(title);
            existingPost.setContent(content);
            postRepository.save(existingPost);
        } else {
            throw new UserNotFoundException("There exists no user with that ID");
        }
    }

    public void deletePost(int id) {
        Optional<Post> postToDelete = findById(id);
        postToDelete.ifPresentOrElse(post -> postRepository.delete(post), () -> {
            throw new UserNotFoundException("There exists no user with the ID to which the post belongs.");
        });
    }




}
