package me.jy.util;

/**
 * @author jy
 */
public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.length() > 0;
    }
}
