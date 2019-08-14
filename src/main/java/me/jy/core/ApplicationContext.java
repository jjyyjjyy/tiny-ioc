package me.jy.core;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author jy
 */
public interface ApplicationContext {

    String getId();

    void setId(String id);

    LocalDateTime getStartTime();

//    ApplicationContext getParent();

    void register(BeanDefinition beanDefinition);

    void scan(String... packages);

    Collection<BeanDefinition> getBeanDefinitions();

    default boolean isActive() {
        return false;
    }

    Environment getEnvironment();

    void refresh();

    void start();

    void stop();
}
