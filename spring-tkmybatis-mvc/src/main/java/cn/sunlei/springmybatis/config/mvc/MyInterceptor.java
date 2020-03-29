package cn.sunlei.springmybatis.config.mvc;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/26 0026 21:43
 */
public class MyInterceptor implements HandlerInterceptor {

    //Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("handler *********************");
        return true; //返回true 才会向下执行
    }

    // 业务处理完成 返回modelandview给dispatherServlet 之后 视图渲染之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("handler1 *********************");
    }

    //DispatcherServlet 渲染了对应的视图之后执行  进行资源清理工作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("handler2 *********************");
    }
}
