package cn.sunlei.springmybatis.mail.freeMarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author sunlei
 * @version 1.0
 * @date 2020/3/29 0029 16:53
 */

@Slf4j
public class FreeMarkerTest {

    public static void main(String[] args) {
        Writer writer = null;
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            File file = ResourceUtils.getFile("classpath:templates/freemarker");
            cfg.setDirectoryForTemplateLoading(file);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Map root = new HashMap();
            root.put("name", "孙磊");
            Template temp = cfg.getTemplate("index.ftl");
            File tempFile = File.createTempFile("testFreeMarker", "ftl");
            log.info("path:" + tempFile.getAbsolutePath());
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile)));
            temp.process(root, writer);
            //tempFile.deleteOnExit(); 安全删除文件并退出
        } catch (Exception e) {
            log.error("test,{}", e);
        } finally {
            if (Objects.nonNull(writer)) {
                try {
                    if (null != writer) {
                        writer.flush();
                        writer.close();
                    }
                } catch (Exception e2) {
                    log.error("test,{}", e2);
                }
            }
        }
        System.out.println("sucess**************");

    }
}
