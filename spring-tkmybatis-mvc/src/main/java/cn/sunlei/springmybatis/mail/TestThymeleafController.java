package cn.sunlei.springmybatis.mail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/28 0028 20:34
 */
@Controller
@RequestMapping("/thymeleaf")
public class TestThymeleafController {

    @GetMapping("/template")
    public String getTemplate(){
     return "test-spring-thymeleaf/test";
    }

    @GetMapping("/template2")
    public String getTemplate2(Map<String,Object> map){
        map.put("username","孙磊");
        return "mail/mail";
    }
}
