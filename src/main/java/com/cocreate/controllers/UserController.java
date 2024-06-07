package com.cocreate.controllers;

import com.cocreate.models.User;
import com.cocreate.services.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {


    @Autowired
    private UserService userService;

/*
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody User newUser) {    // @Valid tells the endpoint to check if there are any constraints when creating a user
        System.out.println(newUser.getUserName());                // the constraints are specified in the user model i.e. @NotEmpty usw
        userService.createUser(newUser);
    }

    @GetMapping("/{id}")
    public void getUser( @PathVariable int id) {
        userService.getUser(id);

    }
*/
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllusers() {
        List<User> greetingResponse = new ArrayList<>();
        greetingResponse = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(greetingResponse);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void createUser(@RequestBody User newUser) {
        userService.createUser(newUser);
    }

}
