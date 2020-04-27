package xyz.seanchao.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void addUser(@RequestParam("name") String username) {
        System.out.println("/register?name=" + username);
        userService.addUser(new User(username, "", ""));
    }

}
