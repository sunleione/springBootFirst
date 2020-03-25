package cn.sunlei.springmybatis.common.reponse;

import cn.sunlei.springmybatis.common.base.BaseRespone;
import lombok.Data;

@Data
public class ResponseDto<T> extends BaseRespone {
    private T data;
}
