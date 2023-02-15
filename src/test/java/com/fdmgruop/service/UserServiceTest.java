package com.fdmgruop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.repository.UserRepository;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindByIdReturnsUserWhenExists() {
        // Arrange
        Integer userId = 1;
        Users user = new Users();
        user.setUserId(userId);
        Optional<Users> expectedUser = Optional.of(user);
        Mockito.when(userRepository.findById(userId)).thenReturn(expectedUser);

        // Act
        Optional<Users> actualUser = userService.findById(userId);

        // Assert
        assertTrue(actualUser.isPresent());
        assertEquals(expectedUser.get(), actualUser.get());
    }

    @Test
 //   @Expected(NoSuchElementException.class)
    public void testFindByIdReturnsEmptyOptionalWhenNotExists() {
        // Arrange
        Integer userId = 1;
        Optional<Users> expectedUser = Optional.empty();
        Mockito.when(userRepository.findById(userId)).thenReturn(expectedUser);

        // Act
        Optional<Users> actualUser = userService.findById(userId);

        // Assert
        assertFalse(actualUser.isPresent());
    }

    @Test
    public void testFindAllUsersReturnsListOfUsers() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(new Users(), new Users(), new Users());
        Mockito.when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> actualUsers = userService.findAllUsers();

        // Assert
        assertEquals(expectedUsers.size(), actualUsers.size());
        assertEquals(expectedUsers, actualUsers);
    }
}
