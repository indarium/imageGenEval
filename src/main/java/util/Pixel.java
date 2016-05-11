package util;

/**
 * author: cvandrei
 * since: 2016-05-11
 */
public class Pixel {

    private Integer hexAsInt;

    /**
     * @param hexString not null and 6 characters long
     * @throws IllegalArgumentException invalid input
     */
    public Pixel(final String hexString) {

        if (hexString == null) throw new IllegalArgumentException("hexString may be not null");
        if (hexString.length() != 6) throw new IllegalArgumentException("hexString may be 6 characters long");

        try {

            this.hexAsInt = Integer.valueOf(hexString, 16);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("hexString is invalid: " + e.getMessage());
        }

    }

    public Integer hexAsInt() {
        return hexAsInt;
    }

    @Override
    public String toString() {
        return "Pixel{" + hexAsInt + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pixel pixel = (Pixel) o;

        return hexAsInt.equals(pixel.hexAsInt);

    }

    @Override
    public int hashCode() {
        return hexAsInt.hashCode();
    }

}
