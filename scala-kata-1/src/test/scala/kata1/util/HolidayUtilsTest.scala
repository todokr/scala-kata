package kata1.util

import java.time.LocalDate

import org.scalatest.funsuite.AnyFunSuite

class HolidayUtilsTest extends AnyFunSuite {

  test("元旦は休日") {
    import HolidayUtils._

    assertResult(true)(isHoliday(LocalDate.of(2019, 1, 1)))
    assertResult(false)(isHoliday(LocalDate.of(2019, 1, 2)))
    assertResult(false)(isHoliday(LocalDate.of(2019, 1, 3)))
    assertResult(true)(isHoliday(LocalDate.of(2019, 1, 5)))
  }
}
