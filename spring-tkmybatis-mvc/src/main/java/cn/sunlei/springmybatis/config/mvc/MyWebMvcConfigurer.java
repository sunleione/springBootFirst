package cn.sunlei.springmybatis.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/26 0026 21:07
 *
 * @decription WebMvcConfigurer 并非只是注册添加拦截器使用，其顾名思义是做Web配置用的
 *             它可以有很很多的配置可以用这个接口自定义的
 *        可以自定义的有：
 *          web客户端request url -> DispatcherServlet  -> 处理器映射器 (HandlerMapping )-> 处理器适配器 (HandlerAdapter )-> 中间业务
 *                           -> 视图解析器(ViewResolver) -> view -> dispatherServlet -> 视图渲染 ->  dispatherServlet -> 客户端
 *                           (View是一个接口，实现类支持不同的View类型（jsp、freemarker、pdf...）)
 *    WebMvcConfigurer 这个接口里有很多抽象方法 里面有很多参数  有哪些就可以自定义哪些  最后添加进来
 *
 *
 */

//加@Configuration 注解 spring容器中也会注入
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    //InterceptorRegistry 拦截器注册器  registry（注册表）
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }
}
