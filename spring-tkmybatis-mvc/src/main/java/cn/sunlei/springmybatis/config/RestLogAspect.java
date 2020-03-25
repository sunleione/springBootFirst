package cn.sunlei.springmybatis.config;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sun.lei on 11/21/2019.
 */

@Aspect
@Component
@Order(1)
@Slf4j
public class RestLogAspect {

    ThreadLocal<Long> timeLocal = new ThreadLocal<>();

    @Pointcut("getMethodPointCut() || postMethodPointCut() || deleteMethodPointCut()")
    public void restMethodPointCut() {
    }

    @Before("restMethodPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("URL : " + request.getRequestURL().toString());
        if (request.getMethod().equalsIgnoreCase("GET")) {
            log.info("request parameter : {}", request.getQueryString());
        } else {
            for (Object object : joinPoint.getArgs()) {
                if (object instanceof HttpServletResponse || object instanceof HttpServletRequest)
                    break;
                log.info("request parameter : {}", JsonUtils.objToString(object));
            }
        }
        timeLocal.set(System.currentTimeMillis());
    }

    @AfterReturning(returning = "ret", pointcut = "restMethodPointCut()")
    public void doAfterReturning(Object ret) {
        log.info("RESPONSE Body: {}", JsonUtils.objToString(ret));
        log.debug("cost time: {} ms", (System.currentTimeMillis() - timeLocal.get()));
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMethodPointCut() {
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMethodPointCut() {
    }

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMethodPointCut() {
    }
}
