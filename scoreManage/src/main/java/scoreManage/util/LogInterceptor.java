package scoreManage.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 输出访问controller
        System.out.println(new Date() + " - Controller: " + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // 输出返回view
        String message;
        if (modelAndView != null) {
            message = new Date() + " - View: " + modelAndView.getViewName();
        } else {
            message = new Date() + " - View: " + "this post does not have a view";
        }
        System.out.println(message);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        // 输出exception
        if (exception != null) {
            System.out.println(new Date() + " - Exception: " + exception.getMessage());
        } else {
            System.out.println(new Date() + " - Exception: " + "all thing is good");
        }
    }
}
