package an.kondratev.onlinestore.servise;

import an.kondratev.onlinestore.dto.UserDTO;
import an.kondratev.onlinestore.model.User;
import an.kondratev.onlinestore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;


    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveNewUser(UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}

