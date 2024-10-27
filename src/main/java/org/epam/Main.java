package org.epam;

import org.epam.controller.UserController;
import org.epam.repository.UserRepository;
import org.epam.service.UserService;
import org.epam.util.mapper.UserMapper;

public class Main {
    public static void main(String[] args) {
        UserMapper userMapper = new UserMapper();
        UserRepository userRepository = new UserRepository(userMapper);
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);
        userController.run();
    }
}