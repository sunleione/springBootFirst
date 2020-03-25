package cn.sunlei.springmybatis.common.request;

import lombok.Getter;
import lombok.Setter;


@Setter
public class BasePageRequest {

    /**
     * 当前页数
     */
    @Getter
    private Integer pageNum = 1;

    /**
     * 每页显示记录数
     */
    private Integer pageSize = 5;

    public Integer getPageSize() {
        return pageSize == -1 ? Integer.MAX_VALUE:pageSize;
    }
}
