package cn.gyt.bs.controller;

import cn.gyt.bs.common.result.model.ApiResult;
import cn.gyt.bs.entity.Admin;
import cn.gyt.bs.service.AdminService;
import cn.gyt.bs.util.ApiResultUtils;
import cn.gyt.bs.util.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 管理员API
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private RedisUtil redisUtil;

    @PostMapping("/login")
    @ResponseBody
    public ApiResult findByUserName(String username, String password) {
        Admin admin = adminService.findByUsername(username);
        if (admin == null) {
            return ApiResultUtils.error("400", "账号不存在");
        }
        if (admin.getPassword().equals(password)) {
//            String token = UUID.randomUUID().toString();
////            //将token缓存，设置缓存时间半小时
////            redisUtil.set(token, token, 60 * 30);
            Admin admin1 = adminService.findAdminInfo(username);
            return ApiResultUtils.success(admin1);
        } else {
            return ApiResultUtils.error("401", "密码错误");
        }
    }

    @GetMapping("/findAdminInfo")
    @ResponseBody
    public ApiResult findAdminInfo(String username) {
        Admin admin = adminService.findAdminInfo(username);
        return ApiResultUtils.success(admin);
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ApiResult updatePassword(String username, String password) {
        adminService.updatePassword(username, password);
        return ApiResultUtils.success();
    }
}
