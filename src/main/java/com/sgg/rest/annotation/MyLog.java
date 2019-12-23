package com.sgg.rest.annotation;

import java.lang.annotation.*;

import com.sgg.rest.enums.BusinessType;

/**
 * 自定义注解类
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {
    String value() default "";
    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;
//    String operType() default "";  // 操作类型
}
