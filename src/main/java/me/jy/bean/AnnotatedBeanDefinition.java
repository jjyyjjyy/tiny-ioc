package me.jy.bean;

import lombok.Data;

import java.lang.annotation.Annotation;

/**
 * @author jy
 */
@Data
public class AnnotatedBeanDefinition implements BeanDefinition {

    private final Class<?> beanClass;

    private Annotation[] annotations = {};

    private String beanName;

    private Object bean;

    private boolean configurationClass;

    private boolean lazy = false;

    private boolean primary = true;

    private ScopeType scopeType = ScopeType.SCOPE_SINGLETON;

    public AnnotatedBeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

}
