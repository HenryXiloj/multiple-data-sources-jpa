package com.henry;

import com.google.common.collect.Iterables;
import com.henry.model.user.User;
import com.henry.repository.user.UserRepository;
import com.henry.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void saveUser_shouldPersistUser() {
/**

 int x = 10;
 int y = 20;
 String result = STR."\{x} + \{y} = \{x + y}";
 string templates are a preview feature and are disabled by default.
 Are string templates supported yet? - IntelliJ support - JetBrains
 https://intellij-support.jetbrains.com/hc/en-us/community/posts/13451540436114-Are-string-templates-supported-yet-
 **/

        // Create a User
        User user = User.builder()
                .id(1L)
                .name("John")
                .lastName("Doe")
                .build();

        when(userRepository.save(any())).thenReturn(user);

        User saved = userService.save(user);

        assertEquals(user, saved);
        verify(userRepository).save(any());
    }

    @Test
    void testFindAllUsers() {
        User user1 = User.builder()
                .id(1L)
                .name("John")
                .lastName("Doe")
                .build();
        User user2 = User.builder()
                .id(2L)
                .name("John")
                .lastName("Doe")
                .build();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        Iterable<User> result = userService.findAll();

        assertEquals(2, Iterables.size(result));
        verify(userRepository).findAll();
    }

    @Test
    void testFindUserById() {
        User user = User.builder()
                .id(1L)
                .name("John")
                .lastName("Doe")
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertEquals(user, result);
        verify(userRepository).findById(1L);
    }
}