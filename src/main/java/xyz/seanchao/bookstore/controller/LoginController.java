package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.seanchao.bookstore.constant.Constant;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.service.UserService;
import xyz.seanchao.bookstore.util.MessageUtil;
import xyz.seanchao.bookstore.util.SessionUtil;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public JSONObject login(@RequestBody Map<String, String> params) {
        String username = params.get(Constant.USERNAME);
        String password = params.get(Constant.PASSWORD);
        System.out.println("/login " + username + " " + password);
        User user = userService.checkUser(username, password);
        System.out.println(user);
        if (user == null) {
            return MessageUtil.makeMessage(1);
        }
        JSONObject userData = new JSONObject();
        userData.put(Constant.USER_ID, user.getId());
        userData.put(Constant.USER_ROLE, user.getRole());
        userData.put(Constant.USERNAME, user.getUsername());
        userData.put("name", user.getName());
        userData.put("address", user.getAddress());
        userData.put("tel", user.getTel());
        SessionUtil.setSession(userData);
        return MessageUtil.makeMessage(0, userData);
    }

    @RequestMapping("/logout")
    public JSONObject logout() {
        boolean status = SessionUtil.removeSession();
        JSONObject res = new JSONObject();
        if (status)
            res.put("status", 0);
        else
            res.put("status", 1);
        System.out.println("logout " + res);
        return res;
    }

    @RequestMapping("/checkSession")
    public JSONObject checkSession() {
        JSONObject auth = SessionUtil.getAuth();
        if (auth == null) {
            return MessageUtil.makeMessage(MessageUtil.makeMessage(1,
                    "invalid session"));
        } else {
            return MessageUtil.makeMessage(0, auth);
        }
    }
}
