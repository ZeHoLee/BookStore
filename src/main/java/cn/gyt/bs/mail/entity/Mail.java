package cn.gyt.bs.mail.entity;

import lombok.Data;

/**
 * <p>Title: Mail</p>
 * <p>Description: </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/9/2 11:07
 */
@Data
public class Mail {

    /**
     * 接受邮箱
     */
    private String to;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 消息ID
     */
    private String msgId;
}
