package com.cocreate.user;


import com.cocreate.exceptions.UserNotFoundException;
import com.cocreate.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public Optional<User> findUserById(int id) {
        if(userRepository.findById(id).isEmpty()) {
            throw  new UserNotFoundException("There exists no user with ID: " + id);
        }
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    } // maybe sort in some order

    public void updateUser(int id, User updateInfo) {
        Optional<User> userToUpdate = findUserById(id);
        if(userToUpdate.isPresent()) {
            User existingUser = userToUpdate.get();
            existingUser.setUserName(updateInfo.getUserName());
            existingUser.setEmailAddress(updateInfo.getEmailAddress());
            existingUser.setPreferredLanguages(updateInfo.getPreferredLanguage());
            userRepository.save(existingUser);
        } else {
            throw new UserNotFoundException("There exists no user with that ID");
        }
    }

    public void deleteUserById(int id) {
        if(userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("There exists no user with ID: " + id);
        }
        userRepository.deleteById(id);
    }

}
