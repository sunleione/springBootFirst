package cn.sunlei.springmybatis.common.reponse;

import cn.sunlei.springmybatis.common.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PageResponse<T> extends BaseResponse {
    private Integer pageNum;
    private Integer pageSize;
    private Integer pageTotal;
    private Long total;
    private List<T> data;

    public static <T> PageResponse constructPageResponse(List<T> list, Long total, Integer pageNum, Integer pageSize) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setData(list);
        pageResponse.setTotal(total);
        pageResponse.setPageNum(pageNum);
        pageResponse.setPageSize(pageSize);
        return pageResponse;
    }
}
