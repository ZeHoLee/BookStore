package cn.gyt.bs.entity;

import lombok.Data;

/**
 * 购物车实体
 *
 * @author Administrator
 */
@Data
public class Cart {

    /**
     * ID
     */
    public int id;

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 书籍Id
     */
    private long bookId;

    /**
     * 购买数量
     */
    private int number;

}
