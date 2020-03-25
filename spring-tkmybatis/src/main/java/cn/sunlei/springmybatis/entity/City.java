package cn.sunlei.springmybatis.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "city")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "provinceid")
    private String provinceId;
}
