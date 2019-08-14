package me.jy.annotation;

import javax.inject.Scope;
import java.lang.annotation.*;

/**
 * @author jy
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope
public @interface Prototype {
}
