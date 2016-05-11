package util;

/**
 * author: cvandrei
 * since: 2016-05-11
 */
public final class StringUtil {

    private StringUtil() {}

    /**
     * Copied from: https://stackoverflow.com/questions/388461/how-can-i-pad-a-string-in-java#388476
     */
    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    /**
     * Copied from: https://stackoverflow.com/questions/388461/how-can-i-pad-a-string-in-java#388476
     */
    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

}
