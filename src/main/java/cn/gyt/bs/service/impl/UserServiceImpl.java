package cn.gyt.bs.service.impl;

import cn.gyt.bs.entity.User;
import cn.gyt.bs.mail.constants.Constants;
import cn.gyt.bs.mapper.UserMapper;
import cn.gyt.bs.service.UserService;
import cn.gyt.bs.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户服务实现类
 *
 * @author Administrator
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据账号查询用户信息
     *
     * @param userId 账号
     * @return 用户信息 {@link User}
     */
    @Override
    public User findUserInfo(long userId) {
        return userMapper.findUserInfo(userId);
    }

    /**
     * 用户注册
     *
     * @param user 用户
     * @return 操作数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(User user) {
        return userMapper.insertUser(user);
    }

    /**
     * 登录
     *
     * @param userId 用户账号
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String login(long userId) {
        return userMapper.login(userId);
    }

    /**
     * 查询邮箱是否被绑定
     *
     * @param email 邮箱
     * @return 数值
     */
    @Override
    public int isBound(String email) {
        return userMapper.isBound(email);
    }

    /**
     * 注册后返回账号
     *
     * @param email 邮箱
     * @return 账号 {@link String}
     */
    @Override
    public String findUserIdByEmail(String email) {
        return userMapper.findUserIdByEmail(email);
    }

    /**
     * 更新地址
     *
     * @param userId   用户账号
     * @param userName 用户名
     * @param phone    电话
     * @param address  地址
     * @return 影响行数
     */
    @Override
    public int updateAddress(long userId, String userName, String phone, String address) {
        return userMapper.updateAddress(userId, userName, phone, address);
    }

    /**
     * 修改密码
     *
     * @param email
     * @param password
     * @return 影响行数
     */
    @Override
    public int updatePassword(String email, String password) {
        return userMapper.updatePassword(email, password);
    }

    /**
     * 修改邮箱
     *
     * @param userId 账号
     * @param email  新的邮箱
     * @return 影响行数
     */
    @Override
    public int updateEmail(long userId, String email) {
        return userMapper.updateEmail(userId, email);
    }
}
