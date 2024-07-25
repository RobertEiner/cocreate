package com.cocreate.post;

import com.cocreate.developer.Developer;
import com.cocreate.developer.DeveloperRepository;
import com.cocreate.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final DeveloperRepository developerRepository;
    private final PostDTOMapper postDTOMapper;

    @Autowired
    public PostService(PostRepository postRepository, DeveloperRepository developerRepository, ModelMapper modelMapper, PostDTOMapper postDTOMapper) {
        this.postRepository = postRepository;
        this.developerRepository = developerRepository;
        this.postDTOMapper = postDTOMapper;
    }

    public PostDTO createPost(Post newPost) {
        postRepository.save(newPost);
        return postDTOMapper.mapToDTO(newPost);
    }

    public PostDTO findById(int id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            return postDTOMapper.mapToDTO(post.get());
        } else {
            throw new ResourceNotFoundException("The post doesn't exist.");
        }
    }

    public void updatePost(int id, String title, String content) {
        Optional<Post> post = postRepository.findById(id);
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
        Optional<Post> postToDelete = postRepository.findById(id);
        postToDelete.ifPresentOrElse(post -> postRepository.delete(post), () -> {
            throw new ResourceNotFoundException("The post doesn't exist.");
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
            throw new ResourceNotFoundException("The developer doesn't exist");
        }
    }





}
