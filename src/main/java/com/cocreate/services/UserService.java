package com.cocreate.services;

import com.cocreate.exceptions.UserNotFoundException;
import com.cocreate.models.User;
import com.cocreate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service // we annotate like this so that we can inject this class into other classes with the @Autowired keyword
public class UserService {

    //@Autowired
    private final UserRepository userRepository;

    // we use controller injection, because field injection is advised against
    @Autowired // tells spring that "hey we need a UserRepository here, can you inject one for us?"
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User newUser) throws DataIntegrityViolationException {
        userRepository.createUser(newUser);
    }


    public Optional<User> findUserById(int id) {
        return userRepository.findUserById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public void deleteUserById(int id) {
        if(userRepository.findUserById(id).isEmpty()) {
            throw new UserNotFoundException("There exists no user with ID: " + id);
        }
         userRepository.deleteUserById(id);
    }

}
