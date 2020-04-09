package cn.gyt.bs.common.result.helper;

import cn.gyt.bs.common.result.model.PageResult;

/**
 * 分页结果返回封装工具类
 *
 * <p>
 * 该工具类操作的实体类是：{@link cn.gyt.bs.common.result.model.PageResult}
 * </p>
 *
 * <ul>
 *     <li>success(String message,T data, long total, int size, long pages ,long current):成功，并返回分页数据</li>
 *     <li>error(String message):失败</li>
 * </ul>
 *
 * @author Administrator
 */
public class PageResultHelper {

    /**
     * 成功，携带描述信息
     *
     * @param message 信息
     * @return 返回结果封装 {@link PageResult}
     */
    public static <S, T> PageResult<S, T> success(String message) {
        return new PageResult<S, T>(message, null);
    }

    /**
     * 成功，携带描述信息、数据、总数、每页条数、总页数、当前页
     *
     * @param message 信息
     * @param data    数据
     * @param total   数据总条数
     * @param size    每页显示条数
     * @param pages   总页数
     * @param current 当前页数
     * @param <T>     数据类型
     * @return 返回结果封装 {@link PageResult}
     */
    public static <S, T> PageResult<S, T> success(String message, T data, long total, int size, long pages, long current) {
        return new PageResult<>(message, data, total, size, pages, current);
    }

    /**
     * 错误，携带详细的描述信息
     *
     * @param message 错误信息
     * @return 返回结果封装 {@link PageResult}
     */
    public static <S, T> PageResult<S, T> error(S code, String message) {
        return new PageResult<S, T>(code, message);
    }
}
