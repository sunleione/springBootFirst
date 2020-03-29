package cn.sunlei.springmybatis.aop;

import cn.sunlei.springmybatis.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/3/26 10:28
 * @Created by sunlei
 */
@Aspect
@Component
@Order(-1)
@Slf4j
public class LogAspect {

    private ThreadLocal<Long> timeLocal = new ThreadLocal<>();

    //@Value("${overLength.log.response.filter}")
    private List<String> responseLogFilterList = new ArrayList<>();

    @Pointcut("(getMethodPointCut() || postMethodPointCut() || deleteMethodPointCut() || putMethodPointCut())")
    public void restMethodPointCut() {
    }

    @Before("restMethodPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("URL : " + request.getRequestURL().toString());
        log.info("METHOD : " + request.getMethod());
        if (request.getMethod().equalsIgnoreCase("GET")) {
            log.info("request parameter : {}", request.getQueryString());
        } else {
            for (Object object : joinPoint.getArgs()) {
                if (object instanceof HttpServletResponse || object instanceof HttpServletRequest || object instanceof MultipartFile[] || object instanceof MultipartFile)
                    break;
                log.info("request parameter : {}", JsonUtils.objToString(object));
            }
        }
        timeLocal.set(System.currentTimeMillis());

    }

    @AfterReturning(returning = "ret", pointcut = "restMethodPointCut()")
    public void doAfterReturning(Object ret) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (responseLogFilterList.contains(request.getRequestURI())) {
            log.info("RESPONSE Body: {}", "--");
        } else {
            log.info("RESPONSE Body: {}", JsonUtils.objToString(ret));
        }
        log.debug("cost time: {} ms", (System.currentTimeMillis() - timeLocal.get()));
    }


    /**
     * 定义切点 切点的写法有很多种
     *   1. 方法签名 定义注解 用注解定义
     *   2. 表达式  匹配  @Pointcut("execution(public * com.another.book.controller.*.*(..))")
     *
     */

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMethodPointCut() {
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMethodPointCut() {
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMethodPointCut() {
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMethodPointCut() {
    }

/*    @Pointcut(value = "!within(com.sunlei.interfaces.service.*)")
    public void excludeFeignClient() {}*/

}

