package me.jy.bean;

import me.jy.context.ApplicationContext;

/**
 * 解析Configuration类, 注册扫描到的bean.
 *
 * @author jy
 */
public class ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private static final ClassPathBeanDefinitionScanner SCANNER = new ClassPathBeanDefinitionScanner();

    @Override
    public void processConfigBeanDefinitions(ApplicationContext applicationContext) {
        applicationContext.getBeanDefinitions()
            .parallelStream()
            .filter(BeanDefinition::isConfigurationClass)
            .forEach(beanDefinition -> {
                Class<?> configurationClass = beanDefinition.getBeanClass();
                SCANNER.scan(configurationClass, applicationContext)
                    .forEach(applicationContext::register);
            });
    }


}
