package an.kondratev.onlinestore.servise;

import an.kondratev.onlinestore.model.User;

import java.util.List;

public interface UserServiceInterface {

    List<User> getUsers();

    User findByEmail(String email);

    void saveNewUser(User user);

    void deleteUserByEmail(String email);

    void updateUser(User updatedUser);

}


