package an.kondratev.onlinestore.servise;

import an.kondratev.onlinestore.dto.UserDTO;
import an.kondratev.onlinestore.model.User;
import an.kondratev.onlinestore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .build();
    }

    @Test
    void testFindById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User foundUser = userService.findById(1L);
        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getName(), foundUser.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUsers() {
        User user2 = User.builder()
                .id(2L)
                .name("Jane Doe")
                .email("jane.doe@example.com")
                .build();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user, user2));
        List<User> users = userService.getUsers();
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testSaveNewUser() {
        UserDTO userDTO = UserDTO.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.saveNewUser(userDTO);
        assertNotNull(savedUser);
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getName(), savedUser.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User updatedUser = userService.updateUser(user);
        assertNotNull(updatedUser);
        assertEquals(user.getName(), updatedUser.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}