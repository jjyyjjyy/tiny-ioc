package me.jy.bean;

import lombok.SneakyThrows;
import me.jy.annotation.ComponentScan;
import me.jy.context.ApplicationContext;
import me.jy.util.StringUtils;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jy
 */
public class ClassPathBeanDefinitionScanner {

    private static final String CLASS_SUFFIX = ".class";

    /**
     * 获取需要扫描bean的包
     *
     * @param configurationClass 配置类
     * @return 包名列表
     */
    private static List<String> getPackagesToScan(Class<?> configurationClass) {
        List<String> packages = new ArrayList<>();
        ComponentScan componentScan = configurationClass.getAnnotation(ComponentScan.class);
        if (componentScan != null) {
            Stream.of(componentScan.value())
                .filter(StringUtils::isNotEmpty)
                .forEach(packages::add);
        }
        if (packages.isEmpty()) {
            packages.add(configurationClass.getPackageName());
        }
        return packages;
    }

    public Set<BeanDefinition> scan(Class<?> configurationClass, ApplicationContext applicationContext) {
        List<String> packagesToScan = getPackagesToScan(configurationClass);
        return packagesToScan.stream()
            .map(packageName -> retrieveBeanDefinition(packageName, applicationContext.getClassLoader()))
            .flatMap(List::stream)
            .collect(Collectors.toSet());
    }

    @SneakyThrows
    public List<BeanDefinition> retrieveBeanDefinition(String packageName, ClassLoader classLoader) {
        String name = packageName.replaceAll("\\.", File.separator);
        Enumeration<URL> resources = classLoader.getResources(name);

        return Files.list(Paths.get(resources.nextElement().getFile()))
            .map(path -> {
                String fileName = path.getFileName().toString();
                String className = packageName + "." + fileName.substring(0, fileName.lastIndexOf(CLASS_SUFFIX)).replace(File.separator, ".");
                try {
                    return AnnotatedBeanDefinitionReader.readBeanDefinition(classLoader.loadClass(className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            })
            .collect(Collectors.toList());
    }
}
