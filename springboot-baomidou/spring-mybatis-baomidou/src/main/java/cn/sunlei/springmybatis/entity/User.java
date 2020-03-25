package cn.sunlei.springmybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 932840053@qq.com
 * @since 2020-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @TableField("name")
    private String name;

    @TableField("department_code")
    private String departmentCode;


    @TableField("department_name")
    private String departmentName;


    @TableField("phone")
    private String phone;


    @TableField("project_no")
    private String projectNo;

    @TableField("email")
    private String email;


}
