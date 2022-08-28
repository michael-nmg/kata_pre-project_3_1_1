package kata.service;

import kata.model.User;

import java.util.List;

public interface UserService {

    void setUser(User user);

    User getUser(Long id);

    List<User> getUsers();

    void updateUser(User user);

    void removeUser(Long id);
}


