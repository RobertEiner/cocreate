package com.cocreate.service;

import com.cocreate.developer.*;
import com.cocreate.post.Post;
import com.cocreate.post.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeveloperServiceTest {

    @InjectMocks
    DeveloperService developerService;

    @Mock
    PostRepository postRepository;

    @Mock
    DeveloperDTOMapper developerDTOMapper;

    @Mock
    DeveloperRepository  developerRepository;

    private Developer developer;
    private DeveloperDTO developerDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        developer = new Developer("Ben", "ben@gmail.com", "Java");
        Post post1 = new Post(1, "Title1", "Content1");
        Post post2 = new Post(2, "Title2", "Content2");
        Post post3 = new Post(3, "Title3", "Content3");
        developer.setPosts(List.of(post1, post2, post3));
        developerDTO = new DeveloperDTO(developer.getUserName(), developer.getEmailAddress(), developer.getPreferredLanguage(), developer.getPosts());
    }

    // -------------- CREATE ------------------
    @Test
    public void should_CreateDeveloper_When_DeveloperIsValid() {
        when(developerRepository.save(any(Developer.class))).thenReturn(developer);
       // when(developerDTOMapper.apply())
    }



}
