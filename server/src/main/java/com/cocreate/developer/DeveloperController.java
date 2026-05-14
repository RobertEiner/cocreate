package com.cocreate.developer;

// Imports
import com.cocreate.post.Post;
import com.cocreate.post.PostService;
import com.cocreate.requests.SignInRequestDTO;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/v1/developers")
@CrossOrigin(origins = "http://localhost:4200")
public class DeveloperController {
    private static final Logger log = LoggerFactory.getLogger(DeveloperController.class);


    private final DeveloperService developerService;
    private final PostService postService;

    @Autowired
    public DeveloperController(DeveloperService developerService, PostService postService) {
        this.developerService = developerService;
        this.postService = postService;
    }

    @GetMapping("/")
    public ResponseEntity<List<DeveloperDTO>> findAllDevelopers() {
        List<DeveloperDTO> developers = developerService.findAllDevelopers();
        return ResponseEntity.status(HttpStatus.OK).body(developers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> findDeveloperById(@PathVariable int id) {
        DeveloperDTO developer = developerService.findDeveloperById(id);
        return ResponseEntity.status(HttpStatus.OK).body(developer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public DeveloperDTO createDeveloper(@RequestBody @Valid Developer newDeveloper) {  // @Valid will trigger the validations when creating a user, as you have set in User.java
        return developerService.createDeveloper(newDeveloper);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeveloper(@PathVariable int id) {
        developerService.deleteDeveloperById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeveloperDTO> updateDeveloper(@PathVariable int id, @RequestBody Developer developer) {
        DeveloperDTO updatedDev = developerService.updateDeveloper(id, developer);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDev);
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<String> createPostForExistingDeveloper(@PathVariable int id, @RequestBody Post post) {
        System.out.println("HERE POST: " + post.getPreferredLanguage());
        log.info("TEST MESSAGE FROM SPRING BOOT " + post.getPreferredLanguage());

        postService.createPostForExistingDeveloper(id, post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/auth")
    public ResponseEntity<DeveloperDTO> authenticateDeveloper(@RequestBody SignInRequestDTO signInRequestDTO )  {
        DeveloperDTO devDTO = developerService.authenticateDeveloper(signInRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(devDTO);
    }
}


