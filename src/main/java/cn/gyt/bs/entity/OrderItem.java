package cn.gyt.bs.entity;

import lombok.Data;

@Data
public class OrderItem {

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 书籍编号
     */
    private long bookId;

    /**
     * 数量
     */
    private int number;

    /**
     * 小计
     */
    private float total;

    /**
     * 书籍
     */
    private Book book;
}
