package cn.gyt.bs.service.impl;

import cn.gyt.bs.entity.OrderItem;
import cn.gyt.bs.mapper.OrderItemMapper;
import cn.gyt.bs.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    public OrderItemMapper ordersItemMapper;

    /**
     * 添加订单商品
     *
     * @param orderItem
     * @return
     */
    @Override
    public int insertOrderItem(OrderItem orderItem) {
        return ordersItemMapper.insertOrderItem(orderItem);
    }
}
