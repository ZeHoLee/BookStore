package cn.gyt.bs.common.result.helper;


import cn.gyt.bs.common.result.model.ApiResult;

/**
 * 接口结果返回封装帮助工具类
 * <p>
 * 该工具类操作的实体是:{@link cn.gyt.bs.common.result.model.ApiResult}
 * </p>
 *
 * <ul>
 *     <li>success(S code, String message, T data):成功，并返回数据</li>
 *     <li>success(S code, String message):成功，不返回数据</li>
 *     <li>error(S code, String message):失败</li>
 * </ul>
 *
 * @author Administrator
 */
public class ApiResultHelper {

    /**
     * 成功，携带状态码及描述信息
     *
     * @param code    状态码
     * @param message 描述信息
     * @param <S>     状态码类型
     * @param <T>     数据类型
     * @return 返回结果封装 {@link ApiResult}
     */
    public static <S, T> ApiResult<S, T> success(S code, String message) {
        return new ApiResult<>(code, message, null);
    }

    /**
     * 成功，携带状态码，描述信息及数据
     *
     * @param code    状态码
     * @param message 描述信息
     * @param data    数据
     * @param <S>     状态码类型
     * @param <T>     数据类型
     * @return 返回结果封装 {@link ApiResult}
     */
    public static <S, T> ApiResult<S, T> success(S code, String message, T data) {
        return new ApiResult<>(code, message, data);
    }

    /**
     * 失败，携带错误码及描述信息
     *
     * @param code    状态码
     * @param message 描述信息
     * @param <S>     错误码类型
     * @param <T>     数据类型
     * @return 返回结果封装 {@link ApiResult}
     */
    public static <S, T> ApiResult<S, T> error(S code, String message) {
        return new ApiResult<>(code, message);
    }

}
