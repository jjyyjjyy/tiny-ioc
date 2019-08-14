package me.jy.context;

import lombok.Data;
import lombok.NonNull;
import me.jy.bean.AnnotatedBeanDefinitionReader;
import me.jy.bean.BeanDefinition;
import me.jy.bean.BeanInitializer;
import me.jy.env.Environment;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static me.jy.context.AnnotationConfigApplicationContext.ApplicationContextState.*;

/**
 * @author jy
 */
@Data
public class AnnotationConfigApplicationContext implements ApplicationContext {

    private static final Map<String, BeanDefinition> BEAN_DEFINITION_MAP = new ConcurrentHashMap<>(1 << 6);
    private static final BeanInitializer BEAN_INITIALIZER = new BeanInitializer();
    private String id;
    private LocalDateTime startTime;
    private Environment environment;
    private ApplicationContextState state = INITIALIZED;

    public AnnotationConfigApplicationContext(@NonNull Class<?> configurationClass) {
        register(AnnotatedBeanDefinitionReader.readBeanDefinition(configurationClass));
        refresh();
    }

    @Override
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void register(BeanDefinition beanDefinition) {
        BEAN_DEFINITION_MAP.put(beanDefinition.getBeanName(), beanDefinition);
    }

    @Override
    public void scan(String... packages) {

    }

    @Override
    public <T> T getBean(Class<T> beanType) {
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = BEAN_DEFINITION_MAP.get(beanName);
        return beanDefinition == null ? null : beanDefinition.getBean();
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanType) {
        return null;
    }

    @Override
    public boolean isActive() {
        return state == RUNNING;
    }

    @Override
    public Collection<BeanDefinition> getBeanDefinitions() {
        return BEAN_DEFINITION_MAP.values();
    }

    @Override
    public synchronized void refresh() {
        initStartTime();
        state = REFRESHING;
        BEAN_DEFINITION_MAP.values().forEach(beanDefinition -> beanDefinition.setBean(BEAN_INITIALIZER.initBean(beanDefinition)));
        state = RUNNING;
    }

    private void initStartTime() {
        if (this.state == INITIALIZED) {
            this.startTime = LocalDateTime.now();
        }
    }

    @Override
    public synchronized void start() {
        initStartTime();
        refresh();
    }

    @Override
    public synchronized void stop() {
        state = STOPPING;
        BEAN_DEFINITION_MAP.clear();
        state = STOPPED;
    }

    public enum ApplicationContextState {
        INITIALIZED, REFRESHING, RUNNING, STOPPING, STOPPED
    }
}
