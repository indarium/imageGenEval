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
     * Generate a 6x1 pixel png image for us. The color is
     *
     * @param r   red value
     * @param g   green value
     * @param b   blue value
     * @param out stream the generated image is written to
     * @throws IOException something failed
     */
    public static final void create(final int r,
                                    final int g,
                                    final int b,
                                    final OutputStream out) throws IOException {

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

            if (out != null) {
                out.flush();
                out.close();
            }

        }

    }

}
