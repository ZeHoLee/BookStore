package cn.gyt.bs.entity;

import lombok.Data;

/**
 * 用户实体
 * <p>用来保存用户实体信息</p>
 *
 * @author Administrator
 */
@Data
public class User {

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱地址
     */
    private String email;
}
