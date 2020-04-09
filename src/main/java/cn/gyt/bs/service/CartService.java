package cn.gyt.bs.service;

import cn.gyt.bs.entity.Cart;

import java.util.List;
import java.util.Map;

/**
 * 购物车服务类
 *
 * @author Administrator
 */
public interface CartService {

    /**
     * 根据用户ID查询购物和
     *
     * @param userId 用户ID
     * @return 购物车集合
     */
    List<Map<String, Object>> findCartByUserId(long userId);

    /**
     * 添加购物车
     *
     * @param cart 购物车实体
     * @return 结果
     */
    int insertCart(Cart cart);

    /**
     * 查询已存在购物车的商品数量
     *
     * @param userId 用户ID
     * @param bookId 书籍ID
     * @return number
     */
    Cart findExistingNum(long userId, long bookId);

    /**
     * 更新购物车内书籍的数量
     *
     * @param id  ID
     * @param number 数量
     * @return 结果
     */
    int updateExistingNumber(int id, int number);

    /**
     * 删除购物车内书籍
     *
     * @param userId 用户账号
     * @param bookId 书籍ID
     * @return 结果
     */
    int deleteCart(long userId, long bookId);
}
