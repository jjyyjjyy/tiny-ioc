package me.jy.bean;

import me.jy.annotation.Prototype;

import javax.inject.Named;
import java.lang.annotation.Annotation;

/**
 * @author jy
 */
public class AnnotatedBeanDefinitionReader {

    public static BeanDefinition readBeanDefinition(Class<?> beanClass) {
        AnnotatedBeanDefinition beanDefinition = new AnnotatedBeanDefinition(beanClass);
        Annotation[] annotations = beanClass.getAnnotations();
        beanDefinition.setAnnotations(annotations);
        if (annotations != null && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation.getClass().equals(Prototype.class)) {
                    beanDefinition.setScopeType(ScopeType.SCOPE_PROTOTYPE);
                } else if (annotation.getClass().equals(Named.class)) {
                    Named named = beanClass.getAnnotation(Named.class);
                    if (named.value().length() > 0) {
                        beanDefinition.setBeanName(named.value());
                    }
                }
            }
        }
        if (beanDefinition.getBeanName() == null) {
            beanDefinition.setBeanName(getDefaultBeanName(beanClass));
        }
        return beanDefinition;
    }

    private static String getDefaultBeanName(Class<?> beanClass) {
        String className = beanClass.getSimpleName();
        if (className.length() > 1) {
            char c = className.charAt(1);
            if (c >= 'A' && c <= 'Z') {
                return className;
            }
        }
        return String.valueOf(className.charAt(0)).toLowerCase() + className.substring(1);
    }
}
