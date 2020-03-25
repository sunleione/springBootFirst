package cn.sunlei.springmybatis.entity;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String departmentName;
    private String departmentCode;
    private String phone;
    private String projectNo;
    private String email;

}
