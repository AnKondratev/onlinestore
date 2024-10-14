package an.kondratev.onlinestore.controller;

import an.kondratev.onlinestore.dto.UserDTO;
import an.kondratev.onlinestore.model.User;
import an.kondratev.onlinestore.servise.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_ShouldReturnSavedUser() {
        UserDTO userDTO = UserDTO.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .build();
        User savedUser = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .orders(Collections.emptyList()).build();
        when(userService.saveNewUser(any(UserDTO.class))).thenReturn(savedUser);
        ResponseEntity<User> response = userController.saveUser(userDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
        verify(userService, times(1)).saveNewUser(userDTO);
    }

    @Test
    void getUsers_ShouldReturnListOfUsers() {
        User user1 = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .orders(Collections.emptyList()).build();
        User user2 = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .orders(Collections.emptyList()).build();
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getUsers()).thenReturn(users);
        ResponseEntity<List<User>> response = userController.getUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
        verify(userService, times(1)).getUsers();
    }

    @Test
    void getUserById_ShouldReturnUser() {
        User user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .orders(Collections.emptyList()).build();

        when(userService.findById(1L)).thenReturn(user);
        ResponseEntity<User> response = userController.getUserById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).findById(1L);
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() {
        User user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .orders(Collections.emptyList()).build();
        when(userService.updateUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).updateUser(user);
    }

    @Test
    void deleteUser_ShouldReturnOkStatus() {
        long idToDelete = 1L;
        HttpStatus response = userController.deleteUser(idToDelete);
        assertEquals(HttpStatus.OK, response);
        verify(userService, times(1)).deleteUser(idToDelete);
    }
}

