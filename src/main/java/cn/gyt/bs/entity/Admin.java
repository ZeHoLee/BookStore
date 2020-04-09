package cn.gyt.bs.entity;

import lombok.Data;

@Data
public class Admin {

    /**
     * Id
     */
    private int id;

    /**
     * 管理员用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String role;
}
