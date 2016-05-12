package util;

/**
 * The padding code has been copied from: https://stackoverflow.com/questions/388461/how-can-i-pad-a-string-in-java#388476
 * <p>
 * author: cvandrei
 * since: 2016-05-11
 */
public final class StringUtil {

    private StringUtil() {
    }

    /**
     * Pad right with character '0'. If the input string is as long (or longer) than the desired length the original
     * string is returned.
     *
     * @param s not null; the string to pad right
     * @param n desired length
     * @return never null
     */
    public static String padRight(final String s, final int n) {
        return padRight(s, n, '0');
    }

    /**
     * Pad right with the specified paddingChar. If the input string is as long (or longer) than the desired length the original
     * string is returned.
     *
     * @param s           not null; the string to pad right
     * @param n           desired length
     * @param paddingChar character to pad with
     * @return never null
     */
    public static String padRight(final String s, final int n, final char paddingChar) {
        if (s == null) throw new IllegalArgumentException("s may not be null");
        return String.format("%1$-" + n + "s", s).replace(' ', paddingChar);
    }

    /**
     * Pad left with character '0'. If the input string is as long (or longer) than the desired length the original
     * string is returned.
     *
     * @param s not null; the string to pad left
     * @param n desired length
     * @return never null
     */
    public static String padLeft(final String s, final int n) {
        return padLeft(s, n, '0');
    }

    /**
     * Pad left with the specified paddingChar. If the input string is as long (or longer) than the desired length the original
     * string is returned.
     *
     * @param s           not null; the string to pad left
     * @param n           desired length
     * @param paddingChar character to pad with
     * @return never null
     */
    public static String padLeft(final String s, final int n, final char paddingChar) {
        if (s == null) throw new IllegalArgumentException("s may not be null");
        return String.format("%1$" + n + "s", s).replace(' ', paddingChar);
    }

}
