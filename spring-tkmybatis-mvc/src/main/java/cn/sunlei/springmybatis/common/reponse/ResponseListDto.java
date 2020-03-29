package cn.sunlei.springmybatis.common.reponse;

import cn.sunlei.springmybatis.common.base.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListDto<T> extends BaseResponse {

    private List<T> data;
}
