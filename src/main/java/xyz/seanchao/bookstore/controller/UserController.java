package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.service.UserService;
import xyz.seanchao.bookstore.util.MessageUtil;

import java.util.List;

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

    @GetMapping("/api/users/all")
    public List<User> allUser() {
        System.out.println("/api/users/all");
        return userService.findAll();
    }

    /**
     * Blocks/Unblocks a user
     *
     * @param userInfo a JSON object including user's id and blocked state
     * @return Wrapped Message
     */
    @PostMapping("/api/users/block")
    public JSONObject blockUser(@RequestBody JSONObject userInfo) {
        System.out.println("/block body: " + userInfo);
        boolean ret = userService.blockUser(userInfo.getInteger("id"),
                userInfo.getInteger("blocked"));
        if (ret) return MessageUtil.makeMessage(0);
        else return MessageUtil.makeMessage(100);
    }
}
