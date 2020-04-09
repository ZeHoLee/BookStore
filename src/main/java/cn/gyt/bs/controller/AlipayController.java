package cn.gyt.bs.controller;

import cn.gyt.bs.common.result.model.ApiResult;
import cn.gyt.bs.config.AlipayConfig;
import cn.gyt.bs.entity.OrderItem;
import cn.gyt.bs.entity.Orders;
import cn.gyt.bs.service.BookService;
import cn.gyt.bs.service.CartService;
import cn.gyt.bs.service.OrdersService;
import cn.gyt.bs.util.ApiResultUtils;
import cn.gyt.bs.util.Constant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static cn.gyt.bs.config.AlipayConfig.*;

@Controller
@RequestMapping("/pay")
@Slf4j
public class AlipayController {

    @Resource
    private OrdersService ordersService;

    @Resource
    private CartService cartService;

    @Resource
    private BookService bookService;

    @PostMapping("/Alipay")
    public String pay(@RequestBody Orders order, HttpServletResponse response) throws AlipayApiException {
        //生成订单编号
        String orderId = UUID.randomUUID().toString().replace("-", "") + 1;
        //设置订单编号
        order.setOrderId(orderId);
        //将订单总价格式化为两位小数
        order.setAmount((float) Math.round(order.getAmount() * 1000) / 1000);
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id,
                merchant_private_key, "json", charset, alipay_public_key, sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + order.getOrderId() + "\"," + "\"total_amount\":\"" + order.getAmount()
                + "\"," + "\"subject\":\"" + order.getOrderId() + "\"," + "\"body\":\"" + order.getOrderId() + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        alipayRequest.setNotifyUrl(notify_url);
        alipayRequest.setReturnUrl(return_url);
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(result);

            //将订单信息写入数据库
            ordersService.insertOrder(order);

            //减库存操作，同步
            synchronized (this) {
                for (OrderItem orderItem : order.getOrderItemList()) {
                    bookService.reduceStock(orderItem.getNumber(), orderItem.getBookId());
                }
            }

            //遍历订单内商品
            for (OrderItem item : order.getOrderItemList()) {
                //删除购物车内相应商品
                cartService.deleteCart(order.getUserId(), item.getBookId());
            }
        } catch (Exception e) {
            log.info("payerror" + result);
        }
        return null;
    }

    @PostMapping("/payForOrder")
    public String payForOrder(@RequestBody Orders order, HttpServletResponse response) throws AlipayApiException {
        //将订单总价格式化为两位小数
        order.setAmount((float) Math.round(order.getAmount() * 1000) / 1000);
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id,
                merchant_private_key, "json", charset, alipay_public_key, sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + order.getOrderId() + "\"," + "\"total_amount\":\"" + order.getAmount()
                + "\"," + "\"subject\":\"" + order.getOrderId() + "\"," + "\"body\":\"" + order.getOrderId() + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        alipayRequest.setNotifyUrl(notify_url);
        alipayRequest.setReturnUrl(return_url);
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(result);
        } catch (Exception e) {
            log.info("payerror" + result);
        }
        return null;
    }

    /**
     * 异步回调
     *
     * @param request      请求
     * @param out_trade_no 订单号
     * @param trade_no     支付宝交易凭证号
     * @param trade_status 交易状态
     * @return
     * @throws UnsupportedEncodingException
     * @throws AlipayApiException
     */
    @PostMapping("/notify")
    public ApiResult alipayNotify(HttpServletRequest request, String out_trade_no, String trade_no, String trade_status) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        log.info("签名：{}", request.getParameterMap());
        log.info(sign_type);
        log.info(alipay_public_key);
        log.info(charset);

        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipay_public_key, charset, sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

        /**
         * 实际验证过程建议商户务必添加以下校验：
         * 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
         * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
         * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
         * 4、验证app_id是否为该商户本身。
         */

        if (signVerified) {//验证成功
            //商户订单号
//                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
//                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
//                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
                //更新订单状态,更新为代发货状态
                ordersService.updateTradeNoAndPaymentTime(trade_no, new Date(), Constant.UN_SEND, out_trade_no);
            }
        } else {//验证失败

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

        //——请在这里编写您的程序（以上代码仅作参考）——
        return ApiResultUtils.success();
    }

    @GetMapping("/return")
    public ApiResult AlipayReturn(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            log.info("订单号:{},支付宝交易号:{},付款金额:{}", out_trade_no, trade_no, total_amount);
            return ApiResultUtils.success();
//            out.println("trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount);
        } else {
//            out.println("验签失败");
        }
        return ApiResultUtils.success();
    }
}
