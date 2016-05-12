package util

import org.specs2.mutable.Specification

/**
  * author: cvandrei
  * since: 2016-05-12
  */
class StringUtilSpec extends Specification {

  "StringUtil.padRight(s, n)" should {

    "with null string results in IllegalArgumentException" in {
      // test & verify
      StringUtil.padRight(null, 1) must throwA[IllegalArgumentException]
    }

    "with string to pad" in {

      // prepare
      val in = "11"

      // test
      val result = StringUtil.padRight(in, in.length + 1)

      // verify
      val expected = in + "0"
      result must_== expected

    }

    "string with length equal to what we would pad to" in {

      // prepare
      val in = "11"

      // test
      val result = StringUtil.padRight(in, in.length)

      // verify
      result must_== in

    }

    "string longer than what we would pad to" in {

      // prepare
      val in = "111"

      // test
      val result = StringUtil.padRight(in, in.length - 1)

      // verify
      result must_== in

    }

  }

  "StringUtil.padRight(s, n, paddingChar)" should {

    "with null string results in IllegalArgumentException" in {
      // test & verify
      StringUtil.padRight(null, 1, '#') must throwA[IllegalArgumentException]
    }

    "with string to pad" in {

      // prepare
      val paddingChar = '#'
      val in = "11"

      // test
      val result = StringUtil.padRight(in, in.length + 1, paddingChar)

      // verify
      val expected = in + paddingChar
      result must_== expected

    }

    "string with length equal to what we would pad to" in {

      // prepare
      val in = "11"

      // test
      val result = StringUtil.padRight(in, in.length, '#')

      // verify
      result must_== in

    }

    "string longer than what we would pad to" in {

      // prepare
      val in = "111"

      // test
      val result = StringUtil.padRight(in, in.length - 1, '#')

      // verify
      result must_== in

    }

  }

  "StringUtil.padLeft(s, n)" should {

    "with null string results in IllegalArgumentException" in {
      // test & verify
      StringUtil.padLeft(null, 1) must throwA[IllegalArgumentException]
    }

    "with string to pad" in {

      // prepare
      val in = "11"

      // test
      val result = StringUtil.padLeft(in, in.length + 1)

      // verify
      val expected = "0" + in
      result must_== expected

    }

    "string with length equal to what we would pad to" in {

      // prepare
      val in = "11"

      // test
      val result = StringUtil.padLeft(in, in.length)

      // verify
      result must_== in

    }

    "string longer than what we would pad to" in {

      // prepare
      val in = "111"

      // test
      val result = StringUtil.padLeft(in, in.length - 1)

      // verify
      result must_== in

    }

  }

  "StringUtil.padLeft(s, n, paddingChar)" should {

    "with null string results in IllegalArgumentException" in {
      // test & verify
      StringUtil.padLeft(null, 1, '#') must throwA[IllegalArgumentException]
    }

    "with string to pad" in {

      // prepare
      val paddingChar = '#'
      val in = "11"

      // test
      val result = StringUtil.padLeft(in, in.length + 1, paddingChar)

      // verify
      val expected = paddingChar + in
      result must_== expected

    }

    "string with length equal to what we would pad to" in {

      // prepare
      val in = "11"

      // test
      val result = StringUtil.padLeft(in, in.length, '#')

      // verify
      result must_== in

    }

    "string longer than what we would pad to" in {

      // prepare
      val in = "111"

      // test
      val result = StringUtil.padLeft(in, in.length - 1, '#')

      // verify
      result must_== in

    }

  }

}
