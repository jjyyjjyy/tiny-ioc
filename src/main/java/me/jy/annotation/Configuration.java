package me.jy.annotation;

import java.lang.annotation.*;

/**
 * @author jy
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Configuration {
}
