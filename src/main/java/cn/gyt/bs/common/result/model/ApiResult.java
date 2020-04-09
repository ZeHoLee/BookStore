package cn.gyt.bs.common.result.model;

/**
 * 接口类型返回封装
 *
 * @author Administrator
 */
public class ApiResult<S, T> extends BaseResult<T> {

    /**
     * 状态码
     */
    private S code;

    /**
     * 无参构造方法
     */
    public ApiResult() {
    }

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 描述信息
     */
    public ApiResult(S code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 描述信息
     * @param data    数据
     */
    public ApiResult(S code, String message, T data) {
        super(message, data);
        this.code = code;
    }

    public S getCode() {
        return code;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ApiResultModel {");
        // code
        if (code != null) {
            stringBuilder.append("\"code\"").append(":").append(code)
                    .append(", ");
        }
        // success
        stringBuilder.append("\"success\"").append(":").append(super.isSuccess())
                .append(", ");
        // message
        stringBuilder.append("\"message\"").append(":")
                .append("\"").append(super.isSuccess()).append("\"");
        // data
        if (super.getData() != null) {
            stringBuilder.append(", ").append("\"data\"").append(":")
                    .append("\"").append(super.getData()).append("\"");
        }
            stringBuilder.append("}");
        return stringBuilder.toString();

    }
}
