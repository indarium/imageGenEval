package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * author: cvandrei
 * since: 2016-05-11
 */
public final class PixelUtil {

    private PixelUtil() {
    }

    private static final int WIDTH = 6;
    private static final int HEIGHT = 1;
    private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;

    /**
     * @param etag not null or empty
     * @return not null
     * @throws IllegalArgumentException invalid input
     */
    public static List<Pixel> etagToPixel(final String etag) {

        if (etag == null || "".equals(etag)) throw new IllegalArgumentException("etag may not be null or empty");

        final String[] array = etag.split("");
        final List<Pixel> l = new ArrayList<>();

        String value = "";
        for (String s : array) {

            value += s;
            if (value.length() == 6) {
                addPixel(value.toUpperCase(), l);
                value = "";
            }

        }

        if (value.length() > 0) {
            final String padded = StringUtil.padRight(value, 6);
            addPixel(padded, l);
        }

        return l;

    }

    /**
     * Convert a 6x1 pixel png image based on an etag.
     *
     * @param etag not null
     * @param out  stream the generated image is written to
     * @throws NullPointerException invalid input
     * @throws IOException          something failed
     */
    public static void create(final String etag,
                              final OutputStream out) throws IOException {

        // TODO automated tests

        if (etag == null) throw new NullPointerException("etag may not be null");
        if (out == null) throw new NullPointerException("out may not be null");

        try {

            final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, IMAGE_TYPE);

            int xPos = 0;
            final List<Pixel> pixelList = etagToPixel(etag);

            for (Pixel p : pixelList) {

                final Integer rgb = p.hexAsInt();
                image.setRGB(xPos++, 0, rgb);

            }

            ImageIO.write(image, "png", out);

        } finally {
            out.flush();
        }

    }

    private static Pixel addPixel(final String hexString,
                                  final List<Pixel> l) {

        final Pixel p = new Pixel(hexString);
        l.add(p);

        return p;

    }

}
