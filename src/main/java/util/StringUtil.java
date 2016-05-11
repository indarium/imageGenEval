package util;

/**
 * The padding code has been copied from: https://stackoverflow.com/questions/388461/how-can-i-pad-a-string-in-java#388476
 *
 * author: cvandrei
 * since: 2016-05-11
 */
public final class StringUtil {

    private StringUtil() {}

    public static String padRight(String s, int n) {
        return padRight(s, n, '0');
    }

    public static String padRight(String s, int n, char paddingChar) {
        return String.format("%1$-" + n + "s", s).replace(' ', paddingChar);
    }

    public static String padLeft(String s, int n) {
        return padLeft(s, n, '0');
    }

    public static String padLeft(String s, int n, char paddingChar) {
        return String.format("%1$" + n + "s", s).replace(' ', paddingChar);
    }

}
