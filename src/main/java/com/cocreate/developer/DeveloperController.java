package com.cocreate.developer;


// Imports
import com.cocreate.post.Post;
import com.cocreate.post.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/developers")
public class DeveloperController {

    private final DeveloperService developerService;
    private final PostService postService;

    @Autowired
    public DeveloperController(DeveloperService developerService, PostService postService) {
        this.developerService = developerService;
        this.postService = postService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Developer>> findAllDevelopers() {
        List<Developer> greetingResponse = developerService.findAllDevelopers();
        return ResponseEntity.status(HttpStatus.OK).body(greetingResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Developer>> findDeveloperById(@PathVariable int id) {
        Optional<Developer> developer = developerService.findDeveloperById(id);
        return ResponseEntity.status(HttpStatus.OK).body(developer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void createDeveloper(@RequestBody @Valid Developer newDeveloper) {  // @Valid will trigger the validations when creating a user, as you have set in User.java
        developerService.createDeveloper(newDeveloper);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeveloper(@PathVariable int id) {
        developerService.deleteDeveloperById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateDeveloper(@PathVariable int id, @RequestBody Developer developer) {
        developerService.updateDeveloper(id, developer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<String> createPostForExistingDeveloper(@PathVariable int id, @RequestBody Post post) {
        postService.createPostForExistingDeveloper(id, post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}


