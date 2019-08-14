package me.jy.core;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static me.jy.core.AnnotationConfigApplicationContext.ApplicationContextState.*;

/**
 * @author jy
 */
@Data
public class AnnotationConfigApplicationContext implements ApplicationContext {

    private static final Map<String, BeanDefinition> BEAN_DEFINITION_MAP = new ConcurrentHashMap<>(1 << 6);
    private String id;
    private LocalDateTime startTime;
    private Environment environment;
    private ApplicationContextState state = INITIALIZED;

    @Override
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void register(BeanDefinition beanDefinition) {

    }

    @Override
    public void scan(String... packages) {

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
        state = REFRESHING;
        // TODO
    }

    @Override
    public synchronized void start() {
        if (this.state == INITIALIZED) {
            this.startTime = LocalDateTime.now();
            refresh();
        }
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

// refresh -> start 先后顺序
// 重复start怎么办?
//
