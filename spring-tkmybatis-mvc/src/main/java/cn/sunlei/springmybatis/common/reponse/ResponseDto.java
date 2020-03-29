package cn.sunlei.springmybatis.common.reponse;

        import cn.sunlei.springmybatis.common.base.BaseResponse;
        import lombok.Data;

@Data
public class ResponseDto<T> extends BaseResponse {
    private T data;
}
