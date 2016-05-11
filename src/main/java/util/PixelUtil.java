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

        /*
         * etag = "406161ad525c9bdf02a21db721f2ffeb"
         *
         * 406161
         * ad525c
         * 9bdf02
         * a21db7
         * 21f2ff
         * eb
         */

        final List<Pixel> pixelList = etagToPixel(etag);
        // TODO iterate over pixelList to create image

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

    public static List<Pixel> etagToPixel(final String etag) {

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

    private static Pixel addPixel(final String s, final List<Pixel> l) {

        final String hexString = s.startsWith("0x") ? s : "0x" + s;
        final Pixel p = new Pixel(hexString);
        l.add(p);

        return p;

    }

}
