package cn.sunlei.springmybatis;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/28 0028 16:09
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {

    @Before
    public void before(){
        log.info("spring test-spring-thymeleaf started");
    }

    @After
    public void after(){
        log.info("spring test-spring-thymeleaf end");
    }
}
