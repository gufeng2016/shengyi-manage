package com.sz.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * <li>类描述：动态数据源注解，可以用在类上，或是方法上</li>
 * @author： amos.zhou
 *  2013-8-1 上午10:05:03
 * @since  v1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChooseDataSource {

    /**
     * 数据库操作类型
     */
    String mainType();
    /**
     * 业务类型
     */
    String bizType() default "";
}
