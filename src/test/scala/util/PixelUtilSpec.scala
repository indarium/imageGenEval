package util

import java.util

import org.specs2.mutable.Specification

/**
  * author: cvandrei
  * since: 2016-05-11
  */
class PixelUtilSpec extends Specification {

  val etag = "406161ad525c9bdf02a21db721f2ffeb"

  val resultingPixelList = Seq(
    new Pixel("406161"),
    new Pixel("ad525c"),
    new Pixel("9bdf02"),
    new Pixel("a21db7"),
    new Pixel("21f2ff"),
    new Pixel("eb0000")
  )

  "PixelUtil.etagToPixel()" should {

    "process valid etag - lower cases" in {

      // test
      val pixelList = PixelUtil.etagToPixel(etag.toLowerCase)

      // verify
      verifyPixelLists(pixelList, resultingPixelList)

    }

    "process valid etag - upper cases" in {

      // test
      val pixelList = PixelUtil.etagToPixel(etag.toUpperCase)

      // verify
      verifyPixelLists(pixelList, resultingPixelList)

    }

    "null etag results in exception" in {
      PixelUtil.etagToPixel(null) must throwA[IllegalArgumentException]
    }

    "empty etag results in exception" in {
      PixelUtil.etagToPixel("") must throwA[IllegalArgumentException]
    }

    "invalid etag results in exception" in {
      PixelUtil.etagToPixel("406161ad525c9bdf02a21db721f2ffgg") must throwA[IllegalArgumentException]
    }

  }

  /*
   * TEST HELPERS
   *******************************************************************/

  private def verifyPixelLists(pixelList: util.List[Pixel], resultingPixelList: Seq[Pixel]) = {

    pixelList.size must_== 6

    val range = Array.range(1, resultingPixelList.size)
    range foreach { i =>
      pixelList.get(i) mustEqual resultingPixelList(i)
    }
    pixelList.get(0) mustEqual resultingPixelList.head

  }

}
