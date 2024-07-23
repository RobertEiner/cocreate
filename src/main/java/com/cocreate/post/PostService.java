package com.cocreate.post;

import com.cocreate.comment.Comment;
import com.cocreate.developer.Developer;
import com.cocreate.developer.DeveloperRepository;
import com.cocreate.exceptions.PostNotFoundException;
import com.cocreate.exceptions.ResourceNotFoundException;
import com.cocreate.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final DeveloperRepository developerRepository;

    @Autowired
    public PostService(PostRepository postRepository, DeveloperRepository developerRepository) {
        this.postRepository = postRepository;
        this.developerRepository = developerRepository;
    }

    public Post createPost(Post newPost) {
        return postRepository.save(newPost);
    }

    public Optional<Post> findById(int id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            return post;
        } else {
            throw new ResourceNotFoundException("The post doesn't exist.");
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
            throw new ResourceNotFoundException("There exists no user with that ID");
        }
    }

    public void deletePost(int id) {
        Optional<Post> postToDelete = findById(id);
        postToDelete.ifPresentOrElse(post -> postRepository.delete(post), () -> {
            throw new ResourceNotFoundException("There exists no user with the ID to which the post belongs.");
        });
    }

    public void createPostForExistingDeveloper(int developerId, Post post) {
        Optional<Developer> developer = developerRepository.findById(developerId);
        if(developer.isPresent()) {
            Post newPost = new Post();
            newPost.setTitle(post.getTitle());
            newPost.setContent(post.getContent());
            newPost.setDeveloper(developer.get());
            postRepository.save(newPost);
        } else {
            throw new ResourceNotFoundException("Developer doesn't exist");
        }
    }





}
