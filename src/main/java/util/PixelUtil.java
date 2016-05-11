package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * author: cvandrei
 * since: 2016-05-11
 */
public final class PixelUtil {

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

        if (etag == null) throw new NullPointerException("etag may not be null");
        if (out == null) throw new NullPointerException("out may not be null");

        // TODO convert etag to RGB pixels
        final int r = 110;
        final int g = 223;
        final int b = 103;

        try {

            final int col = (r << 16) | (g << 8) | b;
            final int width = 6;
            final int height = 1;
            final int imageType = BufferedImage.TYPE_INT_RGB;
            final BufferedImage image = new BufferedImage(width, height, imageType);

            int xPos = 0;
            while (xPos < width) {
                image.setRGB(xPos++, 0, col);
            }

            ImageIO.write(image, "png", out);

        } finally {
            out.flush();
        }

    }

}
