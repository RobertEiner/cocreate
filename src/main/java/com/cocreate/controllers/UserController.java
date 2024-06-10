package com.cocreate.controllers;

// Imports
import com.cocreate.models.User;
import com.cocreate.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> greetingResponse;
        greetingResponse = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(greetingResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findUserById(@PathVariable int id) {
        Optional<User> user = userService.findUserById(id);
        return user.<ResponseEntity<Object>>map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with id: " + id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void createUser(@RequestBody @Valid User newUser) {  // @Valid will trigger the validations when creating a user, as you have set in User.java
       userService.createUser(newUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
