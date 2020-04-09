package cn.gyt.bs.mapper;

import cn.gyt.bs.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {

    /**
     * 添加订单商品
     *
     * @param orderItem
     * @return
     */
    int insertOrderItem(OrderItem orderItem);
}
