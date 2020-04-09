package cn.gyt.bs.mail.controller;


import cn.gyt.bs.common.result.model.ApiResult;
import cn.gyt.bs.mail.constants.Constants;
import cn.gyt.bs.mail.entity.Mail;
import cn.gyt.bs.mail.service.MsgService;
import cn.gyt.bs.service.UserService;
import cn.gyt.bs.util.ApiResultUtils;
import cn.gyt.bs.util.RedisUtil;
import com.mysql.cj.protocol.x.Notice;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/mail")
@Slf4j
public class MailController {

    @Resource
    private MsgService msgService;

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/sendCode")
    @ResponseBody
    public ApiResult sendCode(String email) {
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

//    @RequestMapping(value = "/updateEmail", method = RequestMethod.POST)
//    @ResponseBody
//    public ApiResult updateEmail(String email, String code, String userId, Integer roleId) {
//        //读取缓存
//        String redisCode = (String) redisUtil.get(Constants.MAIL_CODE_PREFIX + userId);
//        log.info("缓存中的验证码：()" + redisUtil.get(Constants.MAIL_CODE_PREFIX + userId));
//        if (redisCode != null && redisCode.equals(code)) {
//            if (roleId.equals(RoleConstant.TEACHER_ROLE_PREFIX)) {
//                teacherService.updateEmail(email, userId);
//                return ApiResultUtils.success();
//            } else if (roleId.equals(RoleConstant.STUDENT_ROLE_PREFIX)) {
//                Integer count = studentService.updateEmail(email, userId);
//                log.info("更新了()条记录", count);
//                return ApiResultUtils.success();
//            } else {
//                return ApiResultUtils.error(null, "更新失败");
//            }
//        } else {
//            return ApiResultUtils.error(null, "验证码已过期或错误");
//        }
//    }

//    @RequestMapping(value = "/sendUpdatePasswordCode", method = RequestMethod.GET)
//    @ResponseBody
//    public ApiResult sendPasswordCode(String email, String userId) {
//        Mail PasswordCode = new Mail();
//        PasswordCode.setTo(email);
//        PasswordCode.setTitle("验证码");
//        String code = String.valueOf(new Random().nextInt(899999) + 100000);
//        PasswordCode.setContent(Constants.MAIL_CONTENT_HEAD + code + Constants.MAIL_CONTENT_FOOT);
//        try {
//            //发送邮件
//            msgService.send(PasswordCode);
//            //保存验证码到缓存中
//            redisUtil.set(Constants.PASSWORD_CODE_PREFIX + userId, code, 60 * 5);
//            log.info("缓存中的验证码：()" + redisUtil.get(Constants.PASSWORD_CODE_PREFIX + userId));
//            return ApiResultUtils.success();
//        } catch (Exception e) {
//            log.info("验证码发送失败:()", e.getMessage());
//            return ApiResultUtils.error(null, "发送失败");
//        }
//    }

//    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//    @ResponseBody
//    public ApiResult updatePassword(String newPassword, String code, String userId) {
//        //读取缓存
//        String redisCode = (String) redisUtil.get(Constants.PASSWORD_CODE_PREFIX + userId);
//
//        if (redisCode != null && redisCode.equals(code)) {
//            try {
//                //加密
//                String password = new SimpleHash("MD5", newPassword, null, 20).toString();
//                userService.updatePassword(userId, password);
//                return ApiResultUtils.success();
//            } catch (Exception e) {
//                log.info("更新失败: ()", e.getMessage());
//                return ApiResultUtils.error(null, "更新失败");
//            }
//        } else {
//            return ApiResultUtils.error(null, "更新失败");
//        }
//    }
}
