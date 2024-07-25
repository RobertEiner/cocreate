/*package com.cocreate.service;


import com.cocreate.user.User;
import com.cocreate.user.UserRepository;
import com.cocreate.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_create_a_user() {
        // Given
        User user = new User("Adam", "adam@gmail.com", "Scala");
        user.setUserId(5);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        User controlUser = userService.createUser(user);

        // Then
        assertEquals(user.getUserName(), controlUser.getUserName());
        assertEquals(user.getEmailAddress(), controlUser.getEmailAddress());

        verify(userRepository).save(user);
    }

}
*/