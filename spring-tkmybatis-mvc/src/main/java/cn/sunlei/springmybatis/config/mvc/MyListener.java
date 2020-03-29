package cn.sunlei.springmybatis.config.mvc;

import cn.sunlei.springmybatis.annotation.ValidError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/26 0026 23:01
 * @PostConstruct 注解的研究 : @PostConstruct该注解被用来修饰一个非静态的void（）方法。
 * Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
 */

@Slf4j
@Component
public class MyListener implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // spring IOC applicationContext 初始化完成(bean都已经加入了容器) 可以做一些校验工作 或项目的准备工作
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


        System.out.println("ApplicationListener  ************");
        //可以获取指定注解的类的信息
        Map<String, Object> beanWhithAnnotation = applicationContext.getBeansWithAnnotation(ValidError.class);
        Set<String> classNames = beanWhithAnnotation.keySet();
        System.out.println("add ValidError class," + classNames.size());
        for (String name : classNames) {
            System.out.println("name:" + name + ",Class:" + beanWhithAnnotation.get(name));
        }
    }

}
