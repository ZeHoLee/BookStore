package cn.gyt.bs.service.impl;

import cn.gyt.bs.entity.Admin;
import cn.gyt.bs.mapper.AdminMapper;
import cn.gyt.bs.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员服务实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin findByUsername(String username) {
        return adminMapper.findByUsername(username);
    }

    /**
     * 根据用户名查询管理员信息
     *
     * @param username 用户名
     * @return 管理员信息 {@link Admin}
     */
    @Override
    public Admin findAdminInfo(String username) {
        return adminMapper.findAdminInfo(username);
    }

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 影响行数
     */
    @Override
    public int updatePassword(String username, String password) {
        return adminMapper.updatePassword(username, password);
    }
}
