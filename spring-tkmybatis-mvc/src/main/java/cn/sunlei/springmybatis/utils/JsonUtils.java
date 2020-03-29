package cn.sunlei.springmybatis.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  建议工具类一般使用abstract
 *   有两个目的：1.不能被实例化
 *               2. 其他工具类可以继承
 */

@Slf4j
public abstract class JsonUtils {

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

    public static <T> String objsToString(List<T> list){
        if(CollectionUtils.isEmpty(list)){
            return "";
        }
        List<String> strList=new ArrayList<>();
        list.forEach(v->{
            strList.add(objToString(v));
        });
        return strList.stream().collect(Collectors.joining(","));
    }

}
