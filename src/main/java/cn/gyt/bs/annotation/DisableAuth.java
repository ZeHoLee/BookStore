package cn.gyt.bs.annotation;


import java.lang.annotation.*;

/**
 * 非鉴权注解，Controller层使用该注解，过滤器将不进行拦截
 *
 * @author Administrator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface DisableAuth {
}
