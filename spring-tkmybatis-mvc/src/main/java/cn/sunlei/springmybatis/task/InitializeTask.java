package cn.sunlei.springmybatis.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/27 0027 0:10
 */

@Component
public class InitializeTask implements CommandLineRunner {

    //服务开启之后 做一些初始化工作
    @Override
    public void run(String... args) throws Exception {
          System.out.println("CommandLineRunner ***********");
    }
}
