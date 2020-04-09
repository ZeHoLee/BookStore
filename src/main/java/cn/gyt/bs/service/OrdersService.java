package cn.gyt.bs.service;

import cn.gyt.bs.entity.OrderItem;
import cn.gyt.bs.entity.Orders;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrdersService {

    /**
     * 添加订单
     *
     * @param orders 订单
     * @return {@link int}
     */
    int insertOrder(Orders orders);

    /**
     * 支付成功，更新支付宝交易号、付款时间以及订单状体
     *
     * @param tradeNo     支付宝交易号
     * @param paymentTime 付款时间
     * @param status      订单状态
     * @param orderId     订单号
     * @return {@link int}
     */
    int updateTradeNoAndPaymentTime(String tradeNo, Date paymentTime, int status, String orderId);

    /**
     * 发货，更新发货时间以及订单状态
     *
     * @param expressId 快递公司代码
     * @param expressNo 快递单号
     * @param orderId   订单号
     * @return {@link int}
     */
    int updateDeliveryTime(int expressId, String expressNo, String orderId);

    /**
     * 确认收货，更新交易完成时间以及订单状态
     *
     * @param orderId 订单号
     * @return {@link int}
     */
    int updateEndTime(String orderId);


    /**
     * 根据用户账号查询，我的订单
     *
     * @param userId 用户账号
     * @param page   页码
     * @param size   大小
     * @return 我的订单 {@link List}
     */
    List<Orders> findMyOrders(long userId, Integer page, Integer size);

    /**
     * 计算我的订单总数
     *
     * @param userId 用户账号
     * @return 数量
     */
    int myOrderCount(long userId);

    /**
     * 根据订单编号查询订单详情
     *
     * @param orderId 订单编号
     * @return 订单信息 {@link cn.gyt.bs.entity.Orders}
     */
    Orders findOrderDetail(String orderId);

    /**
     * 查询所有订单(条件可选)
     *
     * @param status    订单状态
     * @param expressId 快递公司代码
     * @param page      当前页
     * @param size      大小
     * @return 订单列表 {@link List}
     */
    List<Orders> findAll(Integer status, Integer expressId, Integer page, Integer size);

    /**
     * 计算所有订单的总数(条件可选)
     *
     * @param status    订单状态
     * @param expressId 快递公司代码
     * @return 总数
     */
    int allOrdersCount(Integer status, Integer expressId);

    /**
     * 删除订单
     *
     * @param orderId 订单编号
     * @return 结果
     */
    int deleteOrder(String orderId);

    /**
     * 计算昨日订单总额
     *
     * @return 订单总额
     */
    Float yesterdayAmount();

    /**
     * 计算今日订单总额
     *
     * @return 订单总额
     */
    Float todayAmount();

    /**
     * 计算今日订单数
     *
     * @return 订单数
     */
    int todayNumber();

    /**
     * 取消订单
     *
     * @param orderId 订单号
     * @return 影响行数
     */
    int cancelOrder(String orderId);

    /**
     * 统计当月订单数量
     *
     * @return
     */
    List<Map<String, Integer>> ordersNumInMonth();

    /**
     * 统计当月订单进而
     *
     * @return
     */
    List<Map<String, Float>> ordersAmountInMonth();
}
