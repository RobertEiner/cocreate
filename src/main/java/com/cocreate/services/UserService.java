package com.cocreate.services;

import com.cocreate.models.User;
import com.cocreate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //@Autowired
    private final UserRepository userRepository;

    // we use controller injection, because field injection is advised against
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void createUser(User newUser) {
        userRepository.createUser(newUser);
    }

    /*
    public void getUser(int id) {
        userRepository.getUser(id);
    }
*/
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

}
