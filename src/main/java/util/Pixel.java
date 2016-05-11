package util;

/**
 * author: cvandrei
 * since: 2016-05-11
 */
public class Pixel {

    private Integer hexAsInt;

    public Pixel(final String hexString) {
        this.hexAsInt = Integer.valueOf(hexString, 16);
    }

    public Integer getHexAsInt() {
        return hexAsInt;
    }

}
