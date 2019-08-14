package me.jy.context;

import me.jy.demo.DemoBean;
import me.jy.demo.DemoConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jy
 */
class AnnotationConfigApplicationContextTest {

    @Test
    void start() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoConfiguration.class);
        applicationContext.start();
        DemoBean demoBean = (DemoBean) applicationContext.getBean("demoBean");
        Assertions.assertEquals(0, demoBean.dummyInt());
        applicationContext.stop();
    }
}
