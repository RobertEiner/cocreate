package com.cocreate.user;

// Imports
import com.cocreate.user.User;

import com.cocreate.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/users")
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
        return ResponseEntity.status(HttpStatus.OK).body(user);
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

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

