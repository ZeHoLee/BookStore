//package cn.gyt.bs.interceptor;
//
//import cn.gyt.bs.annotation.DisableAuth;
//import cn.gyt.bs.util.RedisUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.serializer.SerializeConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.SecurityUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.Writer;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 自定义鉴权拦截器
// *
// * @author Administrator
// */
//@Slf4j
//@Component
//public class AuthInterceptor extends HandlerInterceptorAdapter {
//
//    @Resource
//    private RedisUtil redisUtil;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        //放行逻辑
//        HandlerMethod method = (HandlerMethod) handler;
//        //获取注解
//        DisableAuth disableAuth = method.getMethod().getAnnotation(DisableAuth.class);
//        if (isDisableAuth(disableAuth)) {
//            return super.preHandle(request, response, handler);
//        }
//
//        String accessToken = getAuthToken(request);
//
//        if (StringUtils.isBlank(accessToken)) {
//            log.info("获取请求头的token:{}", accessToken);
//            setResponse(request, response, "10010", "token已过期，请重新登录");
//            return false;
//        }
//
//        String token = (String) redisUtil.get(accessToken);
//        if (!token.equals(accessToken)) {
//            log.info("当前用户的token:{}", token);
//            setResponse(request, response, "10010", "token已过期，请重新登录");
//            return false;
//        }
//
//        return true;
//    }
//
//    private static boolean isDisableAuth(DisableAuth disableAuth) {
//        return disableAuth != null;
//    }
//
//    /**
//     * 获取请求头或者参数中的token
//     *
//     * @param request
//     * @return
//     */
//    private String getAuthToken(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if (token == null) {
//            token = request.getParameter("Authorization");
//        }
//        return token;
//    }
//
//    public void setResponse(HttpServletRequest request,
//                            HttpServletResponse response, String code, String message) {
//        response.setContentType("application/json;charset=UTF-8");
//        try (Writer writer = response.getWriter()) {
//            Map<String, Object> resultMap = new HashMap<>();
//            resultMap.put("code", code);
//            resultMap.put("message", message);
//
////            JSON.writeJSONString(writer, resultMap);
//            JSON.toJSONStringZ(writer, (SerializeConfig) resultMap);
//            writer.flush();
//        } catch (Exception e) {
//            log.error("response设置操作异常" + e.getMessage());
//        }
//    }
//
//}
