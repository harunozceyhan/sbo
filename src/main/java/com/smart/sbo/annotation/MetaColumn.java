package com.smart.sbo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MetaColumn {
    String type() default "";

    String formType() default "";

    String text() default "";

    String value() default "";

    String url() default "";

    String responseKey() default "";

    String itemText() default "";

    String tableValue() default "";

    boolean required() default false;

    boolean sortable() default false;

    boolean searchable() default false;

    String searchKey() default "";

    boolean showInTable() default false;

    int width() default 20;

    int min() default 1;

    int max() default 25;
}