package cn.sunlei.springmybatis.mail;

import java.util.List;
import java.util.Map;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/28 0028 14:47
 */
public interface MailService {

    /** 简单邮件
     * @param to 发送人
     * @param cc 抄送人
     * @param subject 邮件主题
     * @param content 文本
     */
     void sendSimpleMail(String to, List<String> cc, String subject, String content);

    /**
     *  html邮件
     * @param to 发送人
     * @param cc 抄送人
     * @param subject 邮件主题
     * @param content 带html标签的文本
     */
    void sendHtmlMail(String to,List<String> cc, String subject, String content);

    /**
     *  发送thymeleaf 邮件
     * @param to 发送人
     * @param cc 抄送人
     * @param subject 邮件主题
     * @param path 模版html路径
     * @param params 模版html 参数
     */
    void sendTemplateMail(String to, List<String> cc, String subject, String path, Map<String,Object> params);

    /**
     *  带附件邮件
     * @param to 发送人
     * @param cc 抄送人
     * @param subject 邮件主题
     * @param content 文件内容
     * @param filePath 附件
     */
    void sendAttachmentsMail(String to,List<String> cc, String subject, String content, String filePath);

    /**
     *  邮件发送集成
     * @param mailDO
     */
    void sendMail(MailDO mailDO);

    /**
     *  发送 freeMarker 邮件
     * @param mailDO
     */
    void sendFreeMarkerMail(MailDO mailDO) throws Exception;
}
