package kata1.util

import java.time.LocalDate

import org.scalatest.funsuite.AnyFunSuite

class HolidayUtilsTest extends AnyFunSuite {
  import HolidayUtils._

  test("2021-01-01は金曜だが元旦なので休日") {
    assertResult(true)(isHoliday(LocalDate.of(2021, 1, 1)))
  }

  test("2021-01-02, 2021-01-03は土日で休日") {
    assertResult(true)(isHoliday(LocalDate.of(2021, 1, 2)))
    assertResult(true)(isHoliday(LocalDate.of(2021, 1, 3)))
  }

  test("2021-01-04は月曜で平日") {
    assertResult(false)(isHoliday(LocalDate.of(2021, 1, 4)))
  }
}
