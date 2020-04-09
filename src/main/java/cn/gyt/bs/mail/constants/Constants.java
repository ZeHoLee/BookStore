package cn.gyt.bs.mail.constants;

/**
 * @author Administrator
 */
public interface Constants {

    /**
     * 通知公告队列名称
     */
    String NOTICE_QUEUE_NAME = "notice.queue";

    String NOTICE_FANOUT_EXCHANGE_NAME = "fanout.exchange";

    String NOTICE_EXCHANGE_NAME = "notice.exchange";

    String NOTICE_ROUTING_KEY_NAME = "notice.routing.key";

    String MAIL_CONTENT_HEAD = "【网上书城】你好，您的验证码为：";

    String MAIL_CONTENT_FOOT = "，验证码五分钟内有效。如果非本人操作，请忽略。";

    String MAIL_CODE_PREFIX = "MAIL_CODE";

    String PASSWORD_CODE_PREFIX = "PASSWORD_CODE";
}
