package cn.sunlei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 *   **匹配任意class文件和包，而*只能匹配包，因此无法扫描到包下的类
 *
 * 关于spring @Compenent  scanner.findCandidateComponents() 默认扫描基包 根目录
 */

@SpringBootApplication
@MapperScan(basePackages = {"cn.sunlei.springmybatis.*.dao"})
public class SpringMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisApplication.class, args);
        System.out.println("**success**");
    }

}
