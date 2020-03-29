package cn.sunlei.springmybatis.mail;

import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/28 0028 19:42
 */
@Slf4j
@Component
public class MailUtil {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private FreeMarkerConfigurer configurer;

    /**
     * 通过templateEngine 将tempaltes目录下的html文件转为string
     *
     * @param htmlPath      spring-thymeleaf 默认指定的templates目录下的html文件路径
     * @param htmlParamsMap html文件参数
     * @return string
     */
    public String htmlToString(String htmlPath, Map<String, Object> htmlParamsMap) {
        Context context = new Context();
        if (!CollectionUtils.isEmpty(htmlParamsMap)) {
            htmlParamsMap.forEach((k, v) -> {
                context.setVariable(k, v);
            });
        }
        String process = templateEngine.process(htmlPath, context);
        return process;
    }

    /**
     *  通过Spring FreeMarker 将tempaltes目录下的html文件转为string
     * @param templateName
     * @param htmlParamsMap
     * @return
     * @throws Exception
     */
    public String htmlToStringByFreeMarker(String templateName, Map<String, Object> htmlParamsMap) throws Exception {

        Map<String, Object> model = new HashMap<>();
        model.put("params", htmlParamsMap);
        Template template = configurer.getConfiguration().getTemplate(templateName);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        return text;
    }
}
