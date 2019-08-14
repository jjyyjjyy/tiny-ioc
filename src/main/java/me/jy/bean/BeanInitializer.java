package me.jy.bean;

import lombok.SneakyThrows;

/**
 * @author jy
 */
public class BeanInitializer {

    @SneakyThrows
    public Object initBean(BeanDefinition beanDefinition) {
        return beanDefinition.getBeanClass().newInstance();
    }
}
