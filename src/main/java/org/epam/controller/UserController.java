package org.epam.controller;

import org.epam.models.dto.UserDto;
import org.epam.models.entity.User;
import org.epam.models.request.UpdateRequestUser;
import org.epam.service.UserService;

import java.util.Scanner;

public class UserController {
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void run() {
        boolean run = true;
        while (run) {
            String answer = scanner.next();
            switch (answer) {
                case "create":
                    System.out.println(updateOrCreate(false));
                    break;
                case "find":
                    System.out.print("Enter id: ");
                    String id = scanner.next();
                    System.out.println(userService.getUser(id));
                    break;
                case "update":
                    System.out.println(updateOrCreate(true));
                case "delete":
                    System.out.print("Enter id: ");
                    String id2 = scanner.next();
                    userService.deleteUser(id2);
                    System.out.println("DELETED");
                    break;
                case "find-byemail":
                    System.out.print("Enter email: ");
                    String email = scanner.next();
                    System.out.println(userService.getUserByEmail(email));
                case "q":
                    run = false;
                    break;
                default:
                    System.err.println("Invalid request");
            }
        }
    }

    private UserDto updateOrCreate(boolean update) {
        String id = "";
        if (update) {
            System.out.print("Enter id: ");
            id = scanner.next();
        }
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter first_name: ");
        String firstName = scanner.next();
        System.out.print("Enter last_name: ");
        String lastName = scanner.next();

        if (update) {
            return userService.updateUser(id,
                    new UpdateRequestUser(firstName, lastName, email, password, username));
        } else {
            return userService.createUser(new User(username, password, email, lastName, firstName));
        }
    }
}
