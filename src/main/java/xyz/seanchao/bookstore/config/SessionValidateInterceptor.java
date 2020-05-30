package xyz.seanchao.bookstore.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import xyz.seanchao.bookstore.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionValidateInterceptor extends HandlerInterceptorAdapter {

    /**
     * Before sending the request to controller, intercepts the request
     *
     * @param request  received request
     * @param response response to send
     * @param obj      handler
     * @return boolean value indicating success session validation
     * @throws Exception exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        boolean status = SessionUtil.checkAuth();
        System.out.println(status + " interceptor: " + request);
        if (!status) {
            System.out.println("Session validate failed.");
            JSONObject msg = new JSONObject();
            msg.put("status", 1);
            msg.put("msg", "permission denied");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            try (PrintWriter writer = response.getWriter()) {
                writer.print(msg);
            } catch (IOException e) {
                System.out.println("send json back error");
            }
            return false;
        }
        System.out.println("Session validate succeeded.");
        return true;
    }
}

