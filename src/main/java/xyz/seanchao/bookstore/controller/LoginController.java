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
        JSONObject response = new JSONObject();
        if (user == null) {
            response.put("status", 0);
            return response;
        } else response.put("status", 1);
        response.put("user_id", user.getId());
        response.put("role", user.getRole());
        response.put("username", user.getUsername());
        response.put("name", user.getName());
        response.put("address", user.getAddress());
        response.put("tel", user.getTel());
        SessionUtil.setSession(response);
        return response;
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
}
