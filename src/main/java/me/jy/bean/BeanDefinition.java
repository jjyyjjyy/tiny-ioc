package me.jy.bean;

/**
 * @author jy
 */
public interface BeanDefinition {

    Class<?> getBeanClass();

    String getBeanName();

    Object getBean();

    void setBean(Object object);

    boolean isConfigurationClass();

    boolean isPrimary();

    boolean isLazy();

}
