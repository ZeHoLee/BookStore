package cn.gyt.bs.controller;

import cn.gyt.bs.common.result.model.ApiResult;
import cn.gyt.bs.common.result.model.PageResult;
import cn.gyt.bs.entity.Orders;
import cn.gyt.bs.entity.OrderItem;
import cn.gyt.bs.service.OrdersService;
import cn.gyt.bs.util.ApiResultUtils;
import cn.gyt.bs.util.PageResultUtils;
import com.sun.mail.iap.Literal;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrdersController {

    @Resource
    private OrdersService orderService;


    @PostMapping("/insertOrder")
    public ApiResult insertOrder(@RequestBody Orders orders) {
        orderService.insertOrder(orders);
        return ApiResultUtils.success();
    }

    @GetMapping("/findMyOrders")
    @ResponseBody
    public PageResult findMyOrders(long userId, Integer page, Integer size) {
        List<Orders> list = orderService.findMyOrders(userId, page, size);
        int total = orderService.myOrderCount(userId);
        return PageResultUtils.success(list, total, size, (total / size), size);
    }

    @GetMapping("/findOrderDetail")
    @ResponseBody
    public ApiResult findOrderDetail(String orderId) {
        Orders orders = orderService.findOrderDetail(orderId);
        return ApiResultUtils.success(orders);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public PageResult findAll(Integer status, Integer expressId, Integer page, Integer size) {
        List<Orders> list = orderService.findAll(status, expressId, page, size);
        int total = orderService.allOrdersCount(status, expressId);
        return PageResultUtils.success(list, total, size, (page / size), size);
    }

    @PostMapping("/updateDeliveryTime")
    @ResponseBody
    public ApiResult updateDeliveryTime(int expressId, String expressNo, String orderId) {
        orderService.updateDeliveryTime(expressId, expressNo, orderId);
        return ApiResultUtils.success();
    }

    @GetMapping("/updateEndTime")
    @ResponseBody
    public ApiResult updateEndTime(String orderId) {
        orderService.updateEndTime(orderId);
        return ApiResultUtils.success();
    }

    @GetMapping("/deleteOrder")
    @ResponseBody
    public ApiResult deleteOrder(String orderId) {
        orderService.deleteOrder(orderId);
        return ApiResultUtils.success();
    }

    @GetMapping("/count")
    @ResponseBody
    public ApiResult count() {
        int todayNumber = orderService.todayNumber();
        Float todayAmount = orderService.todayAmount();
        Float yesterdayAmount = orderService.yesterdayAmount();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("todayNumber", todayNumber);
        map.put("todayAmount", todayAmount);
        map.put("yesterdayAmount", yesterdayAmount);
        return ApiResultUtils.success(map);
    }

    @GetMapping("/cancelOrder")
    @ResponseBody
    public ApiResult cancelOrder(String orderId) {
        orderService.cancelOrder(orderId);
        return ApiResultUtils.success();
    }

    @GetMapping("/ordersNumInMonth")
    @ResponseBody
    public ApiResult ordersNumInMonth() {
        List<Map<String, Integer>> list = orderService.ordersNumInMonth();
        log.info("" + list);
        List months = new ArrayList<>();
        List num = new ArrayList<>();
        for (Map map : list) {
            log.info("" + map);
            months.add(map.get("month"));
            num.add(map.get("num"));
        }
        Map result = new HashMap<>(16);
        result.put("months", months);
        result.put("num", num);
        return ApiResultUtils.success(result);
    }

    @GetMapping("/ordersAmountInMonth")
    @ResponseBody
    public ApiResult ordersAmountInMonth() {
        List<Map<String, Float>> list = orderService.ordersAmountInMonth();
        List months = new ArrayList<>();
        List amount = new ArrayList<>();
        for (Map map : list) {
            months.add(map.get("month"));
            amount.add(map.get("amount"));
        }
        Map result = new HashMap<>(16);
        result.put("months", months);
        result.put("amount", amount);
        return ApiResultUtils.success(result);
    }
}
