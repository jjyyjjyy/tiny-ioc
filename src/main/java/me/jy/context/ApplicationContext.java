package me.jy.context;

import me.jy.bean.BeanDefinition;
import me.jy.env.Environment;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author jy
 */
public interface ApplicationContext {

    String getId();

    void setId(String id);

    LocalDateTime getStartTime();

    ClassLoader getClassLoader();

//    ApplicationContext getParent();

    void register(BeanDefinition beanDefinition);

    void scan(String... packages);

    <T> T getBean(Class<T> beanType);

    Object getBean(String beanName);

    <T> T getBean(String beanName, Class<T> beanType);

    Collection<BeanDefinition> getBeanDefinitions();

    default boolean isActive() {
        return false;
    }

    Environment getEnvironment();

    void refresh();

    void start();

    void stop();
}
