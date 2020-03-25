package cn.sunlei.springmybatis.common.base;

import lombok.Data;

/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 */

@Data
public class BaseResponse {

    private String code;
    private String message;
    private String requestId;

}
