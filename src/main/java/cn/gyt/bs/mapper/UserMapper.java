package cn.gyt.bs.mapper;

import cn.gyt.bs.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface UserMapper {

    /**
     * 根据账号查询用户信息
     *
     * @param userId 账号
     * @return 用户信息 {@link User}
     */
    User findUserInfo(long userId);

    /**
     * 用户注册
     *
     * @param user 用户
     * @return 数值
     */
    int insertUser(User user);

    /**
     * 登录
     *
     * @param userId 用户账号
     * @return 密码 {@link String}
     */
    String login(long userId);

    /**
     * 查询邮箱是否被绑定
     *
     * @param email 邮箱
     * @return 数值
     */
    int isBound(String email);

    /**
     * 注册后返回账号
     *
     * @param email 邮箱
     * @return 账号 {@link String}
     */
    String findUserIdByEmail(String email);

    /**
     * 更新地址
     *
     * @param userId   用户账号
     * @param userName 用户名
     * @param phone    电话
     * @param address  地址
     * @return 影响行数
     */
    int updateAddress(long userId, String userName, String phone, String address);

    /**
     * 修改密码
     *
     * @param email    邮箱
     * @param password 密码
     * @return 影响行数
     */
    int updatePassword(String email, String password);

    /**
     * 修改邮箱
     *
     * @param userId 账号
     * @param email  新的邮箱
     * @return 影响行数
     */
    int updateEmail(long userId, String email);
}
