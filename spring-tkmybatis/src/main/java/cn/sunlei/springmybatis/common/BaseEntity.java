package cn.sunlei.springmybatis.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class BaseEntity {

    @Column(name = "id")
    private Integer id;

}
