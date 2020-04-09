package cn.gyt.bs.common.result.helper;

import cn.gyt.bs.common.result.model.BaseResult;

/**
 * 结果返回封装帮助工具类
 * <p>
 * 该工具类操作的实体是：{@link cn.gyt.bs.common.result.model.BaseResult}
 * </p>
 *
 * <ul>
 *     <li>success(String message):成功，不反回数据</li>
 *     <li>success(String message ,T data):成功，返回数据</li>
 *     <li>error(String message):失败</li>
 * </ul>
 *
 * @author Administrator
 */
public class BaseResultHelper {

    /**
     * 成功，携带描述信息
     *
     * @param message 描述信息
     * @param <T>     数据类型
     * @return 返回结果封装{@link cn.lzh.gpms.result.model.BaseResult}
     */
    public static <T> BaseResult<T> success(String message) {
        return new BaseResult<>(message, null);
    }

    /**
     * 成功，携带描述信息和数据
     *
     * @param message 描述信息
     * @param data    数据
     * @param <T>     数据类型
     * @return 返回结果封装{@link cn.lzh.gpms.result.model.BaseResult}
     */
    public static <T> BaseResult<T> success(String message, T data) {
        return new BaseResult<>(message, data);
    }

    /**
     * 错误，携带详细的描述信息
     *
     * @param message 描述信息
     * @param <T>     数据类型
     * @return 返回结果封装{@link cn.lzh.gpms.result.model.BaseResult}
     */
    public static <T> BaseResult<T> error(String message) {
        return new BaseResult<>(message);
    }
}
