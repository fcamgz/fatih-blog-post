package com.fatihblog.api.service;

import com.fatihblog.api.feign.UserClient;
import com.fatihblog.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public User createUser(User user) {
        return userClient.createUser(user);
    }

    public User getUserById(Long id) {
        return userClient.getUserById(id);
    }

    public User getUserByUsername(String username) {
        return userClient.getUserByUsername(username);
    }
}
