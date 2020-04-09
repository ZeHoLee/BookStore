package cn.gyt.bs.common.result.model;

/**
 * 基础返回封装
 *
 * @author Administrator
 */
public class BaseResult<T> {

    /**
     * 返回结果标识，true/false
     */
    public boolean success = false;

    /**
     * 描述信息，错误时，可以在这里填写详细的错误信息
     */
    private String message;

    /**
     * 数据，泛型，可以是数组或对象等等，成功并且需要返回数据时才有该参数
     */
    public T data;

    /**
     * 无参构造方法
     */
    public BaseResult() {
    }

    /**
     * 构造方法
     *
     * @param message 描述信息
     * @param data    数据
     */
    public BaseResult(String message, T data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param message 描述信息
     */
    public BaseResult(String message) {
        this.message = message;
    }



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("baseResult {");
        //success
        stringBuilder.append("\"succsee\"").append(":").append(success)
                .append("，");
        //message
        stringBuilder.append("\"message\"").append(":").append("\"")
                .append(message).append("\"");
        //data
        if(data != null){
            stringBuilder.append("，").append("\"data\"").append(":")
                    .append("\"").append(data).append("\"");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
