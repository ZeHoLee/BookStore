package cn.gyt.bs.util;


import cn.gyt.bs.common.result.helper.BaseResultHelper;
import cn.gyt.bs.common.result.model.BaseResult;

/**
 * 返回结果封装工具类
 * @author Administrator
 */
public class ResultUtils {

    /**
     * 成功
     * @return {@link BaseResult}
     */
    public static BaseResult<?> success(){
        return BaseResultHelper.success("success");
    }

    /**
     * 成功,携带数据
     * @param data 数据
     * @param <T> 数据类型
     * @return {@link BaseResult}
     */
    public static <T> BaseResult <T> success(T data){
        return BaseResultHelper.success("success",data);
    }

    /**
     * 错误，携带详细的错误描述信息
     * @param message 详细的错误描述信息
     * @return {@link BaseResult}
     */
    public  static BaseResult<?> error(String message){
        return BaseResultHelper.error(message);
    }
}
