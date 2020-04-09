package cn.gyt.bs.service;

import cn.gyt.bs.entity.OrderItem;

public interface OrderItemService {
    /**
     * 添加订单商品
     *
     * @param orderItem
     * @return
     */
    int insertOrderItem(OrderItem orderItem);
}
