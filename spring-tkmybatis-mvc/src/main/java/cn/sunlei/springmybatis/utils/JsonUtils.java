package cn.sunlei.springmybatis.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String objToString(T obj) {
        if (obj != null) {
            try {
                return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    public static <T> T stringToObj(String str, Class<T> clazz) {
        if (!StringUtils.isEmpty(str)) {
            try {
                return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

}
