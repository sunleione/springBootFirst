package cn.sunlei.springmybatis.aop;

import cn.sunlei.springmybatis.exception.SMException;
import cn.sunlei.springmybatis.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/27 0027 19:14
 *  后面此切面可以拿走 创建专门的aop工程 用于研究
 *  研究切面注意研究这两个类 ProceedingJoinPoint  JoinPoint
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class ServiceMethodAspect {
    // 保证每个线程都有一个单独的实例
    private ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    //表达式 配置切点 service.* 表示service包下的所有类   service..* service包及其子包下的所有类
    @Pointcut("execution(* cn.sunlei.springmybatis.*.service.impl.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint point) throws NoSuchMethodException {
        System.out.println("ServiceMethodAspect starting ...");
        //获取拦截的方法名
        Signature sig = point.getSignature();
        // Signature(签名) 接口的实现类有method class feild ...用于获取对应的信息
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new SMException("Just intercept method");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();

        // 拦截到的方法所在对象
        System.out.println("被代理的对象:" + target);
        //获取拦截方法的参数
        Method method = msig.getMethod();
        //通过Method 获取方法的注解等等
        log.info("methodClass :{}, methodName : {},params : {}",target,method.getName(),JsonUtils.objsToString(Arrays.asList(point.getArgs())));

    }



    /**
     * 环绕方法,可自定义目标方法执行的时机
     * @param pjd JoinPoint的子接口,添加了
     *            Object proceed() throws Throwable 执行目标方法
     *            Object proceed(Object[] var1) throws Throwable 传入的新的参数去执行目标方法
     *            两个方法
     * @return 此方法需要返回值,返回值视为目标方法的返回值
     */
    @Around("pointcut()")
    public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable{
        Object result = null;
        try {
            //前置通知 before
            System.out.println("aroundMethod...目标方法执行前..." + pjd.getSignature().getName());
            //执行目标方法 也可以自己创建新的参数执行
            //result = pjd.proceed();
            //自己传参执行
            result = pjd.proceed(pjd.getArgs());
            //正常返回通知 AfterReturning
            System.out.println("aroundMethod success...目标方法返回结果后..." + result);
        } catch (RuntimeException e) {
            //异常返回通知
            System.out.println("目标方法执行异常..." + e.getMessage());
            //返回(后置)通知(不管是否发生异常都会通知) After (finally) advice
            System.out.println("目标方法执行后..." + pjd.getSignature().getName()+ "--final 返回(后置)通知");
            throw e;
        }
        //返回(后置)通知(不管是否发生异常都会通知) After (finally) advice
        System.out.println("目标方法执行后..." + pjd.getSignature().getName()+"--final 返回(后置)通知");

        System.out.println("ServiceMethodAspect finished *******");
        return result;
    }





}
