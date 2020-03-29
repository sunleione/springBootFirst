package cn.sunlei.springmybatis.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/28 0028 14:53
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Value("${mail.from}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailUtil mailUtil;

    @Override
    public void sendSimpleMail(String to, List<String> cc, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        if (!CollectionUtils.isEmpty(cc)) {
            simpleMailMessage.setCc((String[]) cc.toArray());
        }
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try {
            mailSender.send(simpleMailMessage);
            log.info("简单邮件已经发送。");
        } catch (Exception e) {
            log.error("发送简单邮件时发生异常: {}", e);
        }
    }

    @Override
    public void sendHtmlMail(String to, List<String> cc, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            if (!CollectionUtils.isEmpty(cc)) {
                helper.setCc((String[]) cc.toArray());
            }
            helper.setText(content, true);
            mailSender.send(message);
            log.info("html邮件已经发送。");
        } catch (MessagingException e) {
            log.info("html邮件发送失败: {}", e);
        }
    }


    @Override
    public void sendTemplateMail(String to, List<String> cc, String subject, String tempaltePath, Map<String, Object> params) {
        Context context = new Context();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k, v) -> {
                context.setVariable(k, v);
            });
        }
        String process = templateEngine.process(tempaltePath, context);
        //*****用tempalte发送邮件的以上代码 实际开发可以放入业务模块service中 因为不同的业务模块模版 和收件人不同
        sendHtmlMail(to, null, subject, process);
    }


    @Override
    public void sendAttachmentsMail(String toMain, List<String> cc, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            if (!CollectionUtils.isEmpty(cc)) {
                helper.setCc((String[]) cc.toArray());
            }
            helper.setSubject(subject);
            helper.setText(content);
            helper.setTo(toMain);
            //添加附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            log.info("带附件邮件已经发送。");
        } catch (MessagingException e) {
            log.info("带附件邮件发送失败:{}", e);
        }

    }


    @Override
    public void sendMail(MailDO mailDO) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(mailDO.getSubject());
            helper.setTo(mailDO.getTo());
            if (!CollectionUtils.isEmpty(mailDO.getCcs())) {
                helper.setCc((String[]) mailDO.getCcs().toArray());
            }
            //存在模版则解析html->String
            if (mailDO.isHtml() && mailDO.isExistTemplate()) {
                mailDO.setContext(mailUtil.htmlToString(mailDO.getTemplatePath(), mailDO.getParams()));
            }
            helper.setText(mailDO.getContext(), mailDO.isHtml());
            //附件
            if (mailDO.isExistAttachment()) {
                FileSystemResource file = new FileSystemResource(new File(mailDO.getAttachmentPath()));
                helper.addAttachment(file.getFilename(), file);
            }
            mailSender.send(message);
            log.info("spring邮件已经发送。");
        } catch (MessagingException e) {
            log.info("spring邮件发送失败: {}", e);
        }
    }

    @Override
    public void sendFreeMarkerMail(MailDO mailDO) throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(mailDO.getSubject());
            helper.setTo(mailDO.getTo());
            if (!CollectionUtils.isEmpty(mailDO.getCcs())) {
                helper.setCc((String[]) mailDO.getCcs().toArray());
            }
            //存在模版则解析html->String
            if (mailDO.isHtml() && mailDO.isExistTemplate()) {
                mailDO.setContext(mailUtil.htmlToStringByFreeMarker(mailDO.getTemplatePath(), mailDO.getParams()));
            }
            helper.setText(mailDO.getContext(), mailDO.isHtml());
            //附件
            if (mailDO.isExistAttachment()) {
                FileSystemResource file = new FileSystemResource(new File(mailDO.getAttachmentPath()));
                helper.addAttachment(file.getFilename(), file);
            }
            mailSender.send(message);
            log.info("spring邮件已经发送。");
        } catch (Exception e) {
            log.error("spring邮件发送失败: {}", e);
            throw e;
        }
    }
}
