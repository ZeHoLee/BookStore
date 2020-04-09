package cn.gyt.bs.util;

import cn.gyt.bs.common.result.helper.ApiResultHelper;
import cn.gyt.bs.common.result.model.ApiResult;

/**
 * API接口返回结果工具类
 * @author Administrator
 */
public class ApiResultUtils {

    /**
     * 成功，携带状态码及描述信息
     * @return {@link ApiResult}
     */
    public static ApiResult<Integer,?> success(){
        return ApiResultHelper.success(200,"success");
    }

    /**
     * 成功，携带状态码、描述信息及数据
     * @param data 数据
     * @param <T>   数据类型
     * @return {@link ApiResult}
     */
    public static <T> ApiResult<Integer,T> success(T data){
        return ApiResultHelper.success(200,"success",data);
    }

    public static ApiResult<String,?> error(String code,String message){
        return  ApiResultHelper.error(code,message);
    }
}
