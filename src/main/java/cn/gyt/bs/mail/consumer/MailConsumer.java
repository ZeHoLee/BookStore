package cn.gyt.bs.mail.consumer;

import cn.gyt.bs.mail.config.RabbitConfig;
import cn.gyt.bs.mail.entity.Mail;
import cn.gyt.bs.mail.util.MailUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>Title: MailConsumer</p>
 * <p>Description:邮件消费者，消费队列中的消息 </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/9/2 14:23
 */
@Component
@Slf4j
public class MailConsumer {

    @Autowired
    private MailUtil mailUtil;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息: {}", mail.toString());

        boolean success = mailUtil.send(mail);

        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();

//        if (success) {
            // 手动消费确认，否则会出现消息重复发送
            channel.basicAck(tag, false);
//        } else {
//            channel.basicNack(tag, false, true);
//        }
    }
}
