package cn.sunlei.springmybatis.common.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 *
 *    GenerationType的类型有
 *
 *    TABLE：使用一个特定的数据库表格来保存主键。
 *    SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
 *    IDENTITY：主键由数据库自动生成（主要是自动增长型）
 *    AUTO：主键由程序控制。
 */

// 抽象类不能实例化  存在的意义就是让子类去实现
@Setter
@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
