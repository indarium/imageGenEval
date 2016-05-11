package debug;

import util.PixelUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * author: cvandrei
 * since: 2016-05-11
 */
public class PixelCreate {

    public static void main(String[] args) throws IOException {

        final String etag = "406161ad525c9bdf02a21db721f2ffeb7582";
        final String path = String.format("/Users/cvandrei/Desktop/%s.png", etag);
        final OutputStream out = new FileOutputStream(path);

        PixelUtil.create(etag, out);

    }

}
