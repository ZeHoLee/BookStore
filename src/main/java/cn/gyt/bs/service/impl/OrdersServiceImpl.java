package cn.gyt.bs.service.impl;

import cn.gyt.bs.entity.Orders;
import cn.gyt.bs.entity.OrderItem;
import cn.gyt.bs.mapper.OrderItemMapper;
import cn.gyt.bs.mapper.OrdersMapper;
import cn.gyt.bs.service.CartService;
import cn.gyt.bs.service.OrdersService;
import cn.gyt.bs.util.Constant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    public OrdersMapper orderMapper;

    @Resource
    public OrderItemMapper orderItemMapper;

    @Resource
    private CartService cartService;

    /**
     * 添加订单
     *
     * @param orders 订单
     * @return {@link int}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertOrder(Orders orders) {
//        //生成订单号
//        String orderId = UUID.randomUUID().toString();
//        //设置订单号
//        orders.setOrderId(orderId);
        orders.setCreateTime(new Date());
        for (OrderItem orderItem : orders.getOrderItemList()) {
            orderItem.setOrderId(orders.getOrderId());
            orderItemMapper.insertOrderItem(orderItem);
        }
        return orderMapper.insertOrders(orders);
    }

//    /**
//     * 更新订单状态
//     *
//     * @param status  订单状态(0：待付款，1：待发货，2：待收货)
//     * @param orderId 订单号
//     * @return {@link int}
//     */
//    @Override
//    public int updateStatus(int status, String orderId) {
//        return orderMapper.updateStatus(status, orderId);
//    }


    /**
     * 支付成功，更新支付宝交易号、付款时间以及订单状体
     *
     * @param tradeNo     支付宝交易号
     * @param paymentTime 付款时间
     * @param status      订单状态
     * @param orderId     订单号
     * @return {@link int}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateTradeNoAndPaymentTime(String tradeNo, Date paymentTime, int status, String orderId) {
        return orderMapper.updateTradeNoAndPaymentTime(tradeNo, paymentTime, status, orderId);
    }

    /**
     * 发货，更新发货时间以及订单状态
     *
     * @param expressId 快递公司代码
     * @param expressNo 快递单号
     * @param orderId   订单号
     * @return {@link int}
     */
    @Override
    public int updateDeliveryTime(int expressId, String expressNo, String orderId) {
        //订单状态改为待收货
        int status = Constant.UN_Receive;
        return orderMapper.updateDeliveryTime(new Date(), status, expressId, expressNo, orderId);
    }

    /**
     * 确认收货，更新交易完成时间以及订单状态
     *
     * @param orderId 订单号
     * @return {@link int}
     */
    @Override
    public int updateEndTime(String orderId) {
        int status = Constant.Received;
        return orderMapper.updateEndTime(new Date(), status, orderId);
    }

    /**
     * 根据用户账号查询，我的订单
     *
     * @param userId 用户账号
     * @param page   页码
     * @param size   大小
     * @return 我的订单 {@link List}
     */
    @Override
    public List<Orders> findMyOrders(long userId, Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        return orderMapper.findMyOrders(userId, page, size);
    }

    /**
     * 计算我的订单总数
     *
     * @param userId 用户账号
     * @return 数量
     */
    @Override
    public int myOrderCount(long userId) {
        return orderMapper.myOrderCount(userId);
    }

    /**
     * 根据订单编号查询订单详情
     *
     * @param orderId 订单编号
     * @return 订单信息 {@link Orders}
     */
    @Override
    public Orders findOrderDetail(String orderId) {
        return orderMapper.findOrderDetail(orderId);
    }

    /**
     * 查询所有订单(条件可选)
     *
     * @param status    订单状态
     * @param expressId 快递公司代码
     * @param page      当前页
     * @param size      大小
     * @return 订单列表 {@link List}
     */
    @Override
    public List<Orders> findAll(Integer status, Integer expressId, Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        return orderMapper.findAll(status, expressId, page, size);
    }

    /**
     * 计算所有订单的总数(条件可选)
     *
     * @param status    订单状态
     * @param expressId 快递公司代码
     * @return 总数
     */
    @Override
    public int allOrdersCount(Integer status, Integer expressId) {
        return orderMapper.allOrdersCount(status, expressId);
    }

    /**
     * 删除订单
     *
     * @param orderId 订单编号
     * @return 结果
     */
    @Override
    public int deleteOrder(String orderId) {
        return orderMapper.deleteOrder(orderId);
    }

    /**
     * 计算昨日订单总额
     *
     * @return 订单总额
     */
    @Override
    public Float yesterdayAmount() {
        return orderMapper.yesterdayAmount();
    }

    /**
     * 计算今日订单总额
     *
     * @return 订单总额
     */
    @Override
    public Float todayAmount() {
        return orderMapper.todayAmount();
    }

    /**
     * 计算今日订单数
     *
     * @return 订单数
     */
    @Override
    public int todayNumber() {
        return orderMapper.todayNumber();
    }

    /**
     * 取消订单
     *
     * @param orderId 订单号
     * @return 影响行数
     */
    @Override
    public int cancelOrder(String orderId) {
        return orderMapper.cancelOrder(orderId);
    }

    /**
     * 统计当月订单数量
     *
     * @return
     */
    @Override
    public List<Map<String, Integer>> ordersNumInMonth() {
        return orderMapper.ordersNumInMonth();
    }

    /**
     * 统计当月订单进而
     *
     * @return
     */
    @Override
    public List<Map<String, Float>> ordersAmountInMonth() {
        return orderMapper.ordersAmountInMonth();
    }
}
