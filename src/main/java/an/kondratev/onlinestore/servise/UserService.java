package an.kondratev.onlinestore.servise;

import an.kondratev.onlinestore.model.User;
import an.kondratev.onlinestore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void saveNewUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Transactional
    @Override
    public void updateUser(User updatedUser) {
        userRepository.save(updatedUser);
    }
}

