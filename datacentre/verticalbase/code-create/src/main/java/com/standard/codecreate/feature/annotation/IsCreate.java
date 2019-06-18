package com.standard.codecreate.feature.annotation;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsCreate {
    //列表头名字
    boolean on() default true;
}
