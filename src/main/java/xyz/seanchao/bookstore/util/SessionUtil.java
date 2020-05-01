package xyz.seanchao.bookstore.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.seanchao.bookstore.constant.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static boolean checkAuth() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);

            if (session != null) {
                Integer userType = (Integer) session.getAttribute(Constant.USER_ROLE);
                return userType != null && userType >= 0;
            }
        }
        return false;
    }

    public static JSONObject getAuth() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);

            if (session != null) {
                JSONObject ret = new JSONObject();
                ret.put(Constant.USER_ID, session.getAttribute(Constant.USER_ID));
                ret.put(Constant.USERNAME, session.getAttribute(Constant.USERNAME));
                ret.put(Constant.USER_ROLE, session.getAttribute(Constant.USER_ROLE));
                return ret;
            }
        }
        return null;
    }

    public static void setSession(JSONObject data) {
        System.out.println("set session: " + data);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession();
            for (Object str : data.keySet()) {
                String key = (String) str;
                Object val = data.get(key);
                session.setAttribute(key, val);
            }
        }
    }

    public static boolean removeSession() {
        System.out.println("remove session");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        return true;
    }
}
