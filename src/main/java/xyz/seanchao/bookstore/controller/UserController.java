package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.service.UserService;
import xyz.seanchao.bookstore.util.MessageUtil;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public JSONObject addUser(@RequestBody JSONObject userInfo) {
        System.out.println("/register body: " + userInfo);
        User user = new User(userInfo.getString("username"),
                userInfo.getString("password"));
        user.setName(userInfo.getString("name"));
        user.setEmail(userInfo.getString("email"));
        int res = userService.addUser(user);
        switch (res) {
            case 3:
                return MessageUtil.makeMessage(3, "Duplicate username.");
            case 4:
                return MessageUtil.makeMessage(4, "Invalid email address.");
        }
        return MessageUtil.makeMessage(0);
    }

}
