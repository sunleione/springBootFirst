package cn.sunlei.springmybatis.valid;

import cn.sunlei.springmybatis.annotation.ValidError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Date 2020/3/26 10:55
 * @Created by sunlei
 *
 *    拓展： 也可以通过 ApplicationContextAware 接口获取 ApplicationContext
 *           通过ApplicationContext。getBeansWithAnnotation()  方法获取指定注解的类的信息
 */
@Slf4j
public class ValidErrorInstance {

    // 获取添加指定注解的 类的信息 与该类的属性信息
    private static Map<Field,Class<?>> allValidErrorMap;

    private ValidErrorInstance(){
    }


    static {

        allValidErrorMap = new HashMap<>();
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(ValidError.class));
        Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents("cn.sunlei.springmybatis");
        for(BeanDefinition beanDefinition:beanDefinitions){
            try {
                log.info("beanDefinition {}",beanDefinition.getBeanClassName());
                //Class加载类名 获取该类的Class对象  通过反射获取该类的所有信息
                for (Field f: Class.forName(beanDefinition.getBeanClassName()).getFields()){
                    //存入Class 和 该类的所有属性Class （为后面反射做准备）
                    allValidErrorMap.put(f,Class.forName(beanDefinition.getBeanClassName()));
                }
            }catch (Exception e){
                log.error("ValidErrorInstance error,{}",e.getMessage());
            }
        }

    }


    public static Map<Field,Class<?>> getAllValidErrorMap(){
        return allValidErrorMap;
    }

}
