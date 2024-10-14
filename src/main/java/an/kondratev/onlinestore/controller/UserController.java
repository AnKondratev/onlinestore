package an.kondratev.onlinestore.controller;

import an.kondratev.onlinestore.dto.UserDTO;
import an.kondratev.onlinestore.dto.Views;
import an.kondratev.onlinestore.model.User;

import an.kondratev.onlinestore.servise.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class UserController {

    private final UserService userServiceInterface;

    @PostMapping("save_user")
    public ResponseEntity<User> saveUser(@RequestBody UserDTO user) {
        return new ResponseEntity<>(userServiceInterface.saveNewUser(user), HttpStatus.OK);
    }

    @JsonView(Views.UserSummary.class)
    @GetMapping("all_users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userServiceInterface.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return new ResponseEntity<>(userServiceInterface.findById(id), HttpStatus.OK);
    }

    @PutMapping("update_user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userServiceInterface.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("delete_user/{id}")
    public HttpStatus deleteUser(@PathVariable long id) {
        userServiceInterface.deleteUser(id);
        return HttpStatus.OK;
    }
}