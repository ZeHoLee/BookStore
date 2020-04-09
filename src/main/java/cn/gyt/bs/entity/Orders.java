package cn.gyt.bs.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Orders {

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 收件人姓名
     */
    private String userName;

    /**
     * 订单总价
     */
    private float amount;

    /**
     * 订单状态
     */
    private int status;

    /**
     * 支付宝交易凭证号
     */
    private String tradeNo;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 订单地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 提交时间
     */
    private Date createTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 快递公司代码
     */
    private int expressId;

    /**
     * 快递单号
     */
    private String expressNo;


    private Express express;

    /**
     * 订单商品
     */
    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
