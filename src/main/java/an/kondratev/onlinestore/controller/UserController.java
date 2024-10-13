package an.kondratev.onlinestore.controller;

import an.kondratev.onlinestore.model.Order;
import an.kondratev.onlinestore.model.User;

import an.kondratev.onlinestore.servise.OrderServiceInterface;
import an.kondratev.onlinestore.servise.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class UserController {

    private final UserService userServiceInterface;
    private final OrderServiceInterface orderServiceInterface;

    @GetMapping("all_users")
    public List<User> getAllUsers() {
        return userServiceInterface.getUsers();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userServiceInterface.findByEmail(email);
    }

    @PostMapping("save_user")
    public String saveNewUser(@RequestBody User user) {
        userServiceInterface.saveNewUser(user);
        return "User saved";
    }

    @DeleteMapping("delete_user/{email}")
    public String deleteUserByEmail(@PathVariable String email) {
        userServiceInterface.deleteUserByEmail(email);
        return "User deleted";
    }

    @PutMapping("update_user")
    public String updateUser(@RequestBody User user) {
        userServiceInterface.updateUser(user);
        return "User updated";
    }

    @PostMapping("save_order")
    public String saveNewOrder(@RequestBody Order order) {
        orderServiceInterface.saveOrder(order);
        return "Order saved";
    }
}