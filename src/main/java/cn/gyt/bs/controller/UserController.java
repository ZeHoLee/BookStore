package cn.gyt.bs.controller;

import cn.gyt.bs.common.result.model.ApiResult;
import cn.gyt.bs.entity.User;
import cn.gyt.bs.mail.constants.Constants;
import cn.gyt.bs.mail.entity.Mail;
import cn.gyt.bs.mail.service.MsgService;
import cn.gyt.bs.service.UserService;
import cn.gyt.bs.util.ApiResultUtils;
import cn.gyt.bs.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.temporal.TemporalAmount;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MsgService msgService;


    /**
     * redis操作类
     */
    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/sendRegisterCode")
    @ResponseBody
    public ApiResult sendCode(String email) {
        if (userService.isBound(email) > 0) {
            return ApiResultUtils.error("10010", "该邮箱已被绑定");
        }
        Mail mail = new Mail();
        mail.setTo(email);
        mail.setTitle("邮箱验证码");
        //生成6位数的验证码
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        mail.setContent(Constants.MAIL_CONTENT_HEAD + code + Constants.MAIL_CONTENT_FOOT);
        try {
            //发送邮件
            msgService.send(mail);
            //保存验证码到缓存中
            redisUtil.set(Constants.MAIL_CODE_PREFIX + email, code, 60 * 5);
            log.info("缓存中的验证码：()" + redisUtil.get(Constants.MAIL_CODE_PREFIX + email));
            return ApiResultUtils.success();
        } catch (Exception e) {
            log.info("验证码发送失败:()", e.getMessage());
            return ApiResultUtils.error(null, "发送失败");
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public ApiResult register(User user, String code) {
        //读取缓存中的验证码
        String redisCode = (String) redisUtil.get(Constants.MAIL_CODE_PREFIX + user.getEmail());
        if (redisCode == null) {
            return ApiResultUtils.error("U10002", "验证码已过期！");
        } else if (!redisCode.equals(code)) {
            return ApiResultUtils.error("U10001", "验证码错误！");
        } else {
            userService.register(user);
            String userId = userService.findUserIdByEmail(user.getEmail());
            return ApiResultUtils.success(userId);
        }
    }

//    @PostMapping("/login")
//    @ResponseBody
//    public ApiResult login(String email, String password, String code) {
//        //读取缓存中的验证码
//        String redisCode = (String) redisUtil.get(Constants.MAIL_CODE_PREFIX + email);
//        if (redisCode == null) {
//            return ApiResultUtils.error("10002", "验证码已过期！");
//        } else if (!redisCode.equals(code)) {
//            return ApiResultUtils.error("10001", "验证码错误！");
//        } else {
//            AuthenticationToken token = new UsernamePasswordToken(email, password);
//
//            try {
//                //尝试登录
//                SecurityUtils.getSubject().login(token);
//                //设置token过期时间
//                SecurityUtils.getSubject().getSession().setTimeout(1000 * 60 * 30);
//                log.info("登录成功");
//            } catch (AuthenticationException e) {
//                if (e instanceof IncorrectCredentialsException) {
//                    return ApiResultUtils.error("401", "密码错误");
//                }
//            } catch (InvalidSessionException e) {
//                e.printStackTrace();
//            }
//            return ApiResultUtils.success();
//        }
//    }

    @PostMapping("/login")
    @ResponseBody
    public ApiResult login(long userId, String password) {
        String password1 = userService.login(userId);
        if (password1 == null) {
            return ApiResultUtils.error("U10001", "账号不存在");
        } else if (password.equals(password1)) {
            //生成token
            String token = UUID.randomUUID().toString();
            redisUtil.expire(token, 60 * 30);
            Map<String, Object> map = new HashMap<>(16);
            map.put("token", token);
            User user = userService.findUserInfo(userId);
            map.put("user", user);
            return ApiResultUtils.success(map);
        } else {
            return ApiResultUtils.error("U10002", "账号或密码错误");
        }
    }

    @PostMapping("/updateAddress")
    @ResponseBody
    public ApiResult updateAddress(long userId, String userName, String phone, String address) {
        userService.updateAddress(userId, userName, phone, address);
        return ApiResultUtils.success();
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ApiResult updatePassword(String password, String code, String email) {
        //读取缓存中的验证码
        String redisCode = (String) redisUtil.get(Constants.MAIL_CODE_PREFIX + email);
        if (redisCode == null) {
            return ApiResultUtils.error("U10002", "验证码已过期！");
        } else if (!redisCode.equals(code)) {
            return ApiResultUtils.error("U10001", "验证码错误！");
        } else {
            userService.updatePassword(email, password);
            return ApiResultUtils.success();
        }
    }

    @GetMapping("/findUserInfo")
    @ResponseBody
    public ApiResult findUserInfo(long userId) {
        User user = userService.findUserInfo(userId);
        return ApiResultUtils.success(user);
    }

    @GetMapping("/sendReviseCode")
    @ResponseBody
    public ApiResult sendReviseCode(String email) {
        Mail mail = new Mail();
        mail.setTo(email);
        mail.setTitle("邮箱验证码");
        //生成6位数的验证码
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        mail.setContent(Constants.MAIL_CONTENT_HEAD + code + Constants.MAIL_CONTENT_FOOT);
        try {
            //发送邮件
            msgService.send(mail);
            //保存验证码到缓存中
            redisUtil.set(Constants.MAIL_CODE_PREFIX + email, code, 60 * 5);
            log.info("缓存中的验证码：()" + redisUtil.get(Constants.MAIL_CODE_PREFIX + email));
            return ApiResultUtils.success();
        } catch (Exception e) {
            log.info("验证码发送失败:()", e.getMessage());
            return ApiResultUtils.error(null, "发送失败");
        }
    }

    @PostMapping("/updateEmail")
    @ResponseBody
    public ApiResult updateEmail(long userId, String email, String code) {
        //读取缓存中的验证码
        String redisCode = (String) redisUtil.get(Constants.MAIL_CODE_PREFIX + email);
        if (redisCode == null) {
            return ApiResultUtils.error("U10002", "验证码已过期！");
        } else if (!redisCode.equals(code)) {
            return ApiResultUtils.error("U10001", "验证码错误！");
        } else {
            userService.updateEmail(userId, email);
            return ApiResultUtils.success();
        }
    }
}
