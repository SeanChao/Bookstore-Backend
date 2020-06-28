package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.service.UserService;
import xyz.seanchao.bookstore.util.MessageUtil;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/api/users/info")
    public User getUserInfo(@RequestParam(name = "id") Integer id) {
        return userService.findOne(id);
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

    @GetMapping("/api/users/stat")
    public List<Map<String, Object>> userSalesStat(@RequestParam("from") String fromStr,
                                                   @RequestParam("to") String toStr) {
        System.out.println("/api/users/stat " + fromStr + " -> " + toStr);
        Date from = fromStr.equals("") ? null : Date.from(Instant.parse(fromStr));
        Date to = toStr.equals("") ? null : Date.from(Instant.parse(toStr));
        return userService.userSalesStat(from, to);
    }
}
