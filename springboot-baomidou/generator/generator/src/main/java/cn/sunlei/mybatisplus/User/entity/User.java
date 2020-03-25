package cn.sunlei.mybatisplus.User.entity;

import cn.sunlei.mybatisplus.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User extends entity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "BPD_member__c")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "bid，bpd，cad，cmd，cad")
    @TableField("department_code")
    private String departmentCode;

    @ApiModelProperty(value = "bid，bpd，cad，cmd，cad")
    @TableField("department_name")
    private String departmentName;

    @ApiModelProperty(value = "BPD_Mobile__c")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "Project_no__c")
    @TableField("project_no")
    private String projectNo;

    @TableField("email")
    private String email;


}
