package cn.sunlei.springmybatis.city.entity;


import cn.sunlei.springmybatis.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "city")
public class City extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "provinceid")
    private String provinceId;
}
