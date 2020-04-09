package cn.gyt.bs.service.impl;

import cn.gyt.bs.entity.Cart;
import cn.gyt.bs.mapper.CartMapper;
import cn.gyt.bs.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 购物车服务实现类
 *
 * @author Administrator
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;

    /**
     * 根据用户ID查询购物和
     *
     * @param userId 用户ID
     * @return 购物车集合
     */
    @Override
    public List<Map<String, Object>> findCartByUserId(long userId) {
        return cartMapper.findCartByUserId(userId);
    }

    /**
     * 添加购物车
     *
     * @param cart 购物车实体
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertCart(Cart cart) {
        //查询购物车是否存在相同的书籍
        Cart cart1 = findExistingNum(cart.getUserId(), cart.getBookId());
        if (cart1 != null) {
            //执行更新逻辑
            //计算添加后的数量
            int newNum = cart1.getNumber() + cart.getNumber();
            return updateExistingNumber(cart1.getNumber(), newNum);
        } else {
            //执行添加操作
            return cartMapper.insertCart(cart);
        }
    }

    /**
     * 查询已存在购物车的商品数量
     *
     * @param userId 用户ID
     * @param bookId 书籍ID
     * @return number
     */
    @Override
    public Cart findExistingNum(long userId, long bookId) {
        return cartMapper.findExistingNum(userId, bookId);
    }

    /**
     * 更新购物车内书籍的数量
     *
     * @param id     ID
     * @param number 数量
     * @return 结果
     */
    @Override
    public int updateExistingNumber(int id, int number) {
        return cartMapper.updateExistingNumber(id, number);
    }


    /**
     * 删除购物车内书籍
     *
     * @param userId 用户账号
     * @param bookId 书籍ID
     * @return 结果
     */
    @Override
    public int deleteCart(long userId, long bookId) {
        return cartMapper.deleteCart(userId, bookId);
    }
}
