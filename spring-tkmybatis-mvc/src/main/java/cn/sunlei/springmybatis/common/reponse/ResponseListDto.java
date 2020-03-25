package cn.sunlei.springmybatis.common.reponse;

import cn.sunlei.springmybatis.common.base.BaseRespone;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListDto<T> extends BaseRespone {

    private List<T> data;
}
