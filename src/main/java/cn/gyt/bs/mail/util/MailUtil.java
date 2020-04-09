package cn.gyt.bs.mail.util;

import cn.gyt.bs.mail.entity.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * <p>Title: MailUtil</p>
 * <p>Description: 邮件工具类</p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/9/2 11:01
 */
@Component
public class MailUtil {
    @Value("$spring.mail.from")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean send(Mail mail) {
        String to = mail.getTo();
        String title = mail.getTitle();
        String content = mail.getContent();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1019265267@qq.com");
        message.setSubject(title);
        message.setText(content);
        message.setTo(to);

        try {
            mailSender.send(message);
            logger.info("邮件发送成功");
            return true;
        } catch (MailException e) {
            logger.error("邮件发送失败，to：{}，title：{}", to, title, e);
            return false;
        }
    }
}
