package com.cocreate.service;

import com.cocreate.developer.*;
import com.cocreate.exceptions.ResourceNotFoundException;
import com.cocreate.post.Post;
import com.cocreate.post.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeveloperServiceTest {

    @InjectMocks
    DeveloperService developerService;
    @Mock
    DeveloperDTOMapper developerDTOMapper;
    @Mock
    DeveloperRepository  developerRepository;

    private Developer developer;
    private DeveloperDTO developerDTO;
    private int developerId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        developer = new Developer("Ben", "ben@gmail.com", "Java");
        developerId = 1;
        Post post1 = new Post(1, "Title1", "Content1");
        Post post2 = new Post(2, "Title2", "Content2");
        Post post3 = new Post(3, "Title3", "Content3");
        developer.setPosts(List.of(post1, post2, post3));
        developerDTO = new DeveloperDTO(developerId, developer.getUserName(), developer.getEmailAddress(), developer.getPreferredLanguage(), developer.getPosts());
    }

    // -------------- CREATE ------------------
    @Test
    public void should_CreateDeveloper_When_DeveloperIsValid() {
        // When
        when(developerRepository.save(any(Developer.class))).thenReturn(developer);
        when(developerDTOMapper.mapToDTO(any(Developer.class))).thenReturn(developerDTO);
        // Then
        DeveloperDTO expected = developerService.createDeveloper(developer);
        assertEquals(expected, developerDTO);
        verify(developerRepository).save(any(Developer.class));
        verify(developerDTOMapper).mapToDTO(any(Developer.class));
    }

    // -------------- GET ------------------
    @Test
    public void should_FindDeveloperByID_When_DeveloperExists() {
        // When
        when(developerRepository.findById(any(Integer.class))).thenReturn(Optional.of(developer));
        when(developerDTOMapper.mapToDTO(any(Developer.class))).thenReturn(developerDTO);
        DeveloperDTO expected = developerService.findDeveloperById(developerId);
        // Then
        assertEquals(expected, developerDTO);
        verify(developerRepository).findById(developerId);
        verify(developerDTOMapper).mapToDTO(any(Developer.class));
    }

    @Test
    public void should_ThrowExceptionInFindDeveloperById_When_DeveloperNotFound() {
        // When
        when(developerRepository.findById(developerId)).thenReturn(Optional.empty());
        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
            developerService.findDeveloperById(developerId);
        });
        verify(developerRepository).findById(developerId);
    }

    // -------------- GET ALL ------------------

    @Test
    public void should_ReturnAllDevelopers_When_ThereExistsDevelopers() {
        // Given
        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();
        Developer developer2 = new Developer("Howard", "howard@gmail.com", "C");
        Developer developer3 = new Developer("Karl", "karl@gmail.com", "Scala");
        developer.setPosts(List.of(post1));
        developer2.setPosts(List.of(post2));
        developer3.setPosts(List.of(post3));
        DeveloperDTO developerDTO1 = new DeveloperDTO(developerId, developer.getUserName(), developer.getEmailAddress(), developer.getPreferredLanguage(), developer.getPosts());
        DeveloperDTO developerDTO2 = new DeveloperDTO(developerId, developer2.getUserName(), developer2.getEmailAddress(), developer2.getPreferredLanguage(), developer2.getPosts());
        DeveloperDTO developerDTO3 = new DeveloperDTO(developerId, developer3.getUserName(), developer3.getEmailAddress(), developer3.getPreferredLanguage(), developer3.getPosts());
        List<DeveloperDTO> expectedDTOs = List.of(developerDTO1, developerDTO2,developerDTO3);
        // When
        when(developerRepository.findAll()).thenReturn(List.of(developer, developer2, developer3));
        // Using one when clause for each instance of developer here, if using thenAnswer it will
        // create a new DTO object for each developer, and actual and expected won't match
        when(developerDTOMapper.mapToDTO(developer)).thenReturn(developerDTO1);
        when(developerDTOMapper.mapToDTO(developer2)).thenReturn(developerDTO2);
        when(developerDTOMapper.mapToDTO(developer3)).thenReturn(developerDTO3);
        List<DeveloperDTO> actualDTOs = developerService.findAllDevelopers();
        // Then
        assertEquals(expectedDTOs, actualDTOs);
        verify(developerRepository).findAll();
        verify(developerDTOMapper, times(3)).mapToDTO(any(Developer.class));
    }

    @Test
    public void should_ReturnEmptyList_WhenNoDevelopersExist() {
        // Given
        List<Developer> developers = new ArrayList<>();
        List<DeveloperDTO> expected = new ArrayList<>();
        // When
        when(developerRepository.findAll()).thenReturn(developers);
        List<DeveloperDTO> actual = developerService.findAllDevelopers();
        // Then
        assertEquals(expected, actual);
        verify(developerRepository).findAll();
    }

    // -------------- UPDATE ------------------

    @Test
    public void should_UpdateDeveloperPropertiesAndReturnDTO_When_UpdateInputExists() {
        // Given
        Developer updateInfo = new Developer();
        updateInfo.setUserName("UpdatedUserName");
        updateInfo.setEmailAddress("updated@gmail.com");
        updateInfo.setPreferredLanguages("C++");
        DeveloperDTO expected = new DeveloperDTO(developerId, "UpdatedUserName", "updated@gmail.com", "C++", developer.getPosts());
        // When
        when(developerRepository.findById(developerId)).thenReturn(Optional.of(developer));
        when(developerRepository.save(developer)).thenReturn(developer);
        when(developerDTOMapper.mapToDTO(developer)).thenReturn(expected);
        DeveloperDTO actual = developerService.updateDeveloper(developerId, updateInfo);
        // Then
        assertEquals(updateInfo.getUserName(), developer.getUserName());
        assertEquals(updateInfo.getEmailAddress(), developer.getEmailAddress());
        assertEquals(updateInfo.getPreferredLanguage(), developer.getPreferredLanguage());
        assertEquals(expected, actual);
        verify(developerRepository).findById(developerId);
        verify(developerRepository).save(developer);
        verify(developerDTOMapper).mapToDTO(developer);

    }

    @Test
    public void should_ThrowExceptionOnUpdate_When_DeveloperNotFound() {
        // Given
        Developer updateInfo = new Developer();
        updateInfo.setUserName("UpdatedUserName");
        updateInfo.setEmailAddress("updated@gmail.com");
        updateInfo.setPreferredLanguages("C++");
        // When
        when(developerRepository.findById(developerId)).thenThrow(ResourceNotFoundException.class);
        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
            developerService.updateDeveloper(developerId, updateInfo);
        });
    }

    // -------------- DELETE ------------------
    @Test
    public void should_DeleteDeveloper_When_DeveloperExists() {
        // When
        when(developerRepository.findById(developerId)).thenReturn(Optional.ofNullable(developer));
        developerService.deleteDeveloperById(developerId);
        // Then
        verify(developerRepository).findById(developerId);
        verify(developerRepository).deleteById(developerId);
    }

    @Test
    public void should_ThrowExceptionOnDelete_When_DeveloperNotFound() {
        // When
        when(developerRepository.findById(developerId)).thenThrow(ResourceNotFoundException.class);
        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
           developerService.deleteDeveloperById(developerId);
        });
        verify(developerRepository, never()).deleteById(developerId);
    }


}
