package util

import org.specs2.mutable.Specification

/**
  * author: cvandrei
  * since: 2016-05-11
  */
class PixelSpec extends Specification {

  "Pixel constructor" should {

    "valid hex string - upper case" in {

      // prepare
      val hexString = "9BDF02"

      // test
      val pixel = new Pixel(hexString)

      // verify
      val expected = Integer.valueOf(hexString, 16)
      pixel.hexAsInt must_== expected

    }

    "valid hex string - lower case" in {

      // prepare
      val hexString = "9bdf02"

      // test
      val pixel = new Pixel(hexString)

      // verify
      val expected = Integer.valueOf(hexString, 16)
      pixel.hexAsInt must_== expected

    }

    "invalid hex string - too short" in {

      // prepare
      val hexString = "9bdf0"

      // test && verify
      new Pixel(hexString) must throwA[IllegalArgumentException]

    }

    "invalid hex string - too long" in {

      // prepare
      val hexString = "9bdf022"

      // test && verify
      new Pixel(hexString) must throwA[IllegalArgumentException]

    }

    "invalid hex string - character: 'g'" in {

      // prepare
      val hexString = "9bdg02"

      // test && verify
      new Pixel(hexString) must throwA[IllegalArgumentException]

    }

  }

}
