package cn.sunlei.springmybatis.mail;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/28 0028 19:33
 *
 *  text和html模式先统一都用String context代替
 *   后面专门写一个html-- template解析 --> String context的工具类
 */
@Data
public class MailDO {
    private String to;
    private List<String> ccs;
    private String subject;
    private String context;
    //为true时有两种情况 1 自己写html标签字符串 2 解析模版template
    private boolean isHtml;
    private boolean existTemplate;
    private String templatePath;
    private boolean existAttachment;
    private String attachmentPath;
    private Map<String,Object> params;
}
