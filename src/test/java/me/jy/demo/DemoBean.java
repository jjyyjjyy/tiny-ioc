package me.jy.demo;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Arrays;

/**
 * @author jy
 */
@Named
@Singleton
public class DemoBean {

    public DemoBean() {
        System.out.println("DemoBean constructor called...");
    }

    public static void main(String[] args) {
        Arrays.stream(DemoBean.class.getAnnotations()).forEach(System.out::println);
    }

    public int dummyInt() {
        return 0;
    }
}
