package cn.gyt.bs.mail.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: MsgDTO</p>
 * <p>Description: </p>
 *
 * @author lizihao
 * @version 1.0.0
 * @date 2019/9/2 15:36
 */
@Data
public class MsgDTO implements Serializable {

    private static final long SERIALIZABLE_ID = 48848755485555L;

    private Integer status;

    private String msg;

    private Object data;

    public MsgDTO(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }



}
