package me.jy.annotation;


import java.lang.annotation.*;

/**
 * Bean method marker.
 *
 * @author jy
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Bean {

    String value() default "";
}
