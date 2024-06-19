package com.cocreate.repository;

import com.cocreate.user.User;
import com.cocreate.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // annotation to let spring know that the tests are in this file
public class UserRepositoryTests {


    private UserRepository userRepository;

    @Test
    public void whenCreateNewUser_thenReturnUserName() {
        // Given
        User user = new User("Adam", "adam@gmail.com", "Scala");

        // then



    }


}
