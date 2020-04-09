package cn.gyt.bs.service;

import cn.gyt.bs.entity.Admin;

/**
 * 管理员服务接口
 */
public interface AdminService {

    /**
     * 根据用户名查询管理员信息
     *
     * @param username 用户名
     * @return 管理员信息 {@link Admin}
     */
    Admin findByUsername(String username);

    /**
     * 根据用户名查询管理员信息
     *
     * @param username 用户名
     * @return 管理员信息 {@link Admin}
     */
    Admin findAdminInfo(String username);

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 影响行数
     */
    int updatePassword(String username, String password);
}
