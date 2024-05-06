package pl.doleckijakub.mc.util;

public final class EnglishUtil {

    public static String pluralize(int n, String verb) {
        String result = n + " " + verb;
        if (n != 1) result += "s";
        return result;
    }

    public static String firstUpper(String s) {
        return (s.charAt(0) + "").toUpperCase() + s.substring(1).toLowerCase();
    }

}
