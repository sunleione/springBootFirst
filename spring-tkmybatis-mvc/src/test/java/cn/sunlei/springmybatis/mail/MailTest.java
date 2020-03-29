package cn.sunlei.springmybatis.mail;

import cn.sunlei.springmybatis.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/28 0028 16:19
 */
@Slf4j
public class MailTest extends BaseTest {

    @Autowired
    private MailService mailService;

    @Test
    public void simpleMailTest(){
          mailService.sendSimpleMail("932840053@qq.com",null,"simple thymeleaf","simple thymeleaf one");
    }

    @Test
    public void htmlMailTest(){
        mailService.sendHtmlMail("932840053@qq.com",null,"simple thymeleaf","<h3>你看我<span style=\"font-size: 35px\" th:text=\"${username}\"></span>, 哈哈哈!</h3>");
    }

    @Test
    public void templateMailTest(){
        Map<String,Object> params = new HashMap<>();
        params.put("username","孙磊");
        mailService.sendTemplateMail("932840053@qq.com",null,"html temlate thymeleaf","thymeleaf/mail",params);
    }

    @Test
    public void fileMailTest(){
        mailService.sendAttachmentsMail("932840053@qq.com",null,"file thymeleaf","带附件的邮件","E:\\daima\\spring-tkmybatis-mvc\\src\\main\\resources\\banner.txt");
    }

    @Test
    public void mailTest(){
        MailDO mailDO =new MailDO();
        mailDO.setTo("932840053@qq.com");
        mailDO.setSubject("spring thymeleaf 集成");
        mailDO.setHtml(true);
        mailDO.setExistTemplate(true);
        mailDO.setTemplatePath("thymeleaf/mail");
        Map<String,Object> params=new HashMap<>();
        params.put("username","sunlei 孙磊 spring thymeleaf 集成");
        mailDO.setExistAttachment(true);
        String s = StringUtils.cleanPath("E:\\daima\\spring-tkmybatis-mvc\\src\\main\\resources\\banner.txt");
        log.info("s:" + s);
        mailDO.setAttachmentPath(s);
        mailService.sendMail(mailDO);
    }

    @Test
    public void mailTreeMarkerTest() throws Exception{
        MailDO mailDO =new MailDO();
        mailDO.setTo("932840053@qq.com");
        mailDO.setSubject("spring thymeleaf 集成 3/29");
        mailDO.setHtml(true);
        mailDO.setExistTemplate(true);
        mailDO.setTemplatePath("freemarker/mail.html");
        Map<String,Object> params=new HashMap<>();
        params.put("messageCode","sunlei001");
        params.put("messageStatus","500");
        params.put("cause","cause");
        mailDO.setExistAttachment(true);
        File file = ResourceUtils.getFile("classpath:banner.txt");
        log.info("bannerLocation:" + file.getAbsolutePath());
        mailDO.setAttachmentPath(file.getAbsolutePath());
        mailService.sendFreeMarkerMail(mailDO);
    }



}
