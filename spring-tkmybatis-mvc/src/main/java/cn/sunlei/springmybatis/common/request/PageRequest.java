package cn.sunlei.springmybatis.common.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest<T> extends BasePageRequest {
    /**
     * 查询条件
     */
    private T data;

    /**
     * 排序列
     */
    private String orderByColumn = "uid";
    /**
     * true -> asc
     * false -> desc
     * default is true
     */
    private Boolean asc = false;

    public PageRequest(T t) {
        this.data = t;
    }

    public PageRequest() {}
}
