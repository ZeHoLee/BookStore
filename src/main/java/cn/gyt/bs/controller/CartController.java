package cn.gyt.bs.controller;

import cn.gyt.bs.common.result.model.ApiResult;
import cn.gyt.bs.entity.Cart;
import cn.gyt.bs.service.CartService;
import cn.gyt.bs.util.ApiResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 购物车控制器
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Resource
    private CartService cartService;

    @GetMapping("/findCartByUserId")
    @ResponseBody
    public ApiResult findCartByUserId(long userId) {
        List<Map<String, Object>> cart = cartService.findCartByUserId(userId);
        return ApiResultUtils.success(cart);
    }

    @PostMapping("/insertCart")
    @ResponseBody
    public ApiResult insertCart(Cart cart) {
        int res = cartService.insertCart(cart);
        return ApiResultUtils.success();
    }

    @PostMapping("/updateExistingNumber")
    @ResponseBody
    public ApiResult updateExistingNumber(int id, int number) {
        cartService.updateExistingNumber(id, number);
        return ApiResultUtils.success();
    }

    @GetMapping("/deleteCart")
    @ResponseBody
    public ApiResult deleteCart(long userId, long bookId) {
        cartService.deleteCart(userId, bookId);
        return ApiResultUtils.success();
    }
}
