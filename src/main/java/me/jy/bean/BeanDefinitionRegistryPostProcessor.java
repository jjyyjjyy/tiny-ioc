package me.jy.bean;

import me.jy.context.ApplicationContext;

/**
 * @author jy
 */
public interface BeanDefinitionRegistryPostProcessor {

    void processConfigBeanDefinitions(ApplicationContext applicationContext);
}
