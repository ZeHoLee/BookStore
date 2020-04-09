package cn.gyt.bs.util;

import cn.gyt.bs.common.result.helper.PageResultHelper;
import cn.gyt.bs.common.result.model.PageResult;

/**
 * @author Administrator
 */
public class PageResultUtils {

    /**
     * 成功，携带分页相关数据及信息
     *
     * @param data    数据
     * @param total   数据总条数
     * @param size    每页条数
     * @param pages   总页数
     * @param current 当前页
     * @param <T>     数据类型
     * @return {@link PageResult}
     */
    public static <S, T> PageResult<S,T> success(T data, long total, int size, long pages, long current) {
        return PageResultHelper.success("success", data, total, size, pages, current);
    }

    /**
     * 失败，携带详细信息
     *
     * @param message 失败信息
     * @return {@link PageResult}
     */
    public static PageResult<?, ?> error(String code, String message) {
        return PageResultHelper.error(code, message);
    }
}
