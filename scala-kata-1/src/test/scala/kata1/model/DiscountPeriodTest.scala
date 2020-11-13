package kata1.model

import java.time.LocalDateTime

import org.scalatest.funsuite.AnyFunSuite

class DiscountPeriodTest extends AnyFunSuite {

  private def drive(enteredAt: LocalDateTime,
                    exitedAt: LocalDateTime): HighwayDrive = {
    HighwayDrive(
      driver = Driver(0),
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Urban,
      enteredAt = enteredAt,
      exitedAt = exitedAt
    )
  }
  test("hasIntersection: 割引時間帯 matches 走行記録の全体") {
    val period = DiscountPeriod(0, 4)
    val start = LocalDateTime.of(2020, 10, 1, 0, 0)
    val end = LocalDateTime.of(2020, 10, 1, 4, 0)
    val d = drive(start, end)

    val actual = period.hasIntersection(d)

    assertResult(true)(actual)
  }

  test("hasIntersection: 割引時間帯 contains 走行記録の全体") {
    val period = DiscountPeriod(startHour = 0, endHour = 4)
    val start = LocalDateTime.of(2020, 9, 30, 23, 59)
    val end = LocalDateTime.of(2020, 10, 1, 4, 1)
    val d = drive(start, end)

    val actual = period.hasIntersection(d)

    assertResult(true)(actual)
  }

  test("hasIntersection: 割引時間帯 contains 走行の開始") {
    val period = DiscountPeriod(startHour = 0, endHour = 4)
    val start = LocalDateTime.of(2020, 10, 1, 0, 0)
    val end = LocalDateTime.of(2020, 10, 1, 1, 0)
    val d = drive(start, end)

    val actual = period.hasIntersection(d)

    assertResult(true)(actual)
  }

  test("hasIntersection: 割引時間帯 contains 走行の終了") {
    val period = DiscountPeriod(startHour = 0, endHour = 4)
    val start = LocalDateTime.of(2020, 9, 30, 23, 0)
    val end = LocalDateTime.of(2020, 10, 1, 0, 0)
    val d = drive(start, end)

    val actual = period.hasIntersection(d)

    assertResult(true)(actual)
  }

  test("hasIntersection: 走行記録 contains 割引時間帯") {
    val period = DiscountPeriod(startHour = 0, endHour = 4)
    val start = LocalDateTime.of(2020, 9, 30, 23, 0)
    val end = LocalDateTime.of(2020, 10, 1, 5, 0)
    val d = drive(start, end)

    val actual = period.hasIntersection(d)

    assertResult(true)(actual)
  }

  test("hasIntersection: 割引時間帯 does not contain 走行記録") {
    val period = DiscountPeriod(startHour = 6, endHour = 9)
    val start = LocalDateTime.of(2020, 11, 5, 9, 1)
    val end = LocalDateTime.of(2020, 11, 5, 9, 30)
    val d = drive(start, end)

    val actual = period.hasIntersection(d)

    assertResult(false)(actual)
  }
}
