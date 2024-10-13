package an.kondratev.onlinestore.servise;

import an.kondratev.onlinestore.dto.UserDTO;
import an.kondratev.onlinestore.model.User;

import java.util.List;

public interface UserServiceInterface {

    List<User> getUsers();

    User findById(long id);

    User saveNewUser(UserDTO userDTO);

    User updateUser(User updatedUser);

    void deleteUser(long id);

}


