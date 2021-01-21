package com.mdsoftware.trackingsystemsdk;

public final class StringUtils {

    public static boolean isEmpty(String s) {
        if (s != null && !s.isEmpty()) {
            return false;
        }

        return true;
    }

    public static boolean isNotEmpty(String s) {
        if (s != null && !s.isEmpty()) {
            return true;
        }

        return false;
    }
}
