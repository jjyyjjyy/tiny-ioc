package me.jy.annotation;

import java.lang.annotation.*;

/**
 * @author jy
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComponentScan {

    String[] value() default {};
}
