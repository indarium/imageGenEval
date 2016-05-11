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

    /*
    PHP Code:

    function getImage($id) {
        $width = 6;
        $height = 1;
        $img = imagecreatetruecolor($width, $height);

        $id .= rand(1000, 9999);
        $ids = str_split($id);

        for($i = 0, $j = 0; $i < count($ids); $i+=6, $j++) {
            $hex1 = $ids[$i] . $ids[$i+1];
            $hex2 = $ids[$i+2] . $ids[$i+3];
            $hex3 = $ids[$i+4] . $ids[$i+5];

            $color = imagecolorallocatealpha(
                $img, hexdec($hex1), hexdec($hex2),
                hexdec($hex3), 0
            );

            imagesetpixel($img, $j, 0, $color);
        }
        return $img;
    }
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
