package an.kondratev.onlinestore.servise;

import an.kondratev.onlinestore.dto.UserDTO;
import an.kondratev.onlinestore.model.Order;
import an.kondratev.onlinestore.model.User;
import an.kondratev.onlinestore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        if (userDTO.getOrders() != null) {
            List<Order> orders = userDTO.getOrders().stream()
                    .map(orderDTO -> Order.builder()
                            .orderName(orderDTO.getOrderName())
                            .totalAmount(orderDTO.getTotalAmount())
                            .status(orderDTO.getStatus())
                            .user(user)
                            .build())
                    .collect(Collectors.toList());
            user.setOrders(orders);
        }
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
