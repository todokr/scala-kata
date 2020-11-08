package kata1

import java.time.LocalDateTime

import kata1.model.{
  AreaType,
  DiscountPercentage,
  Driver,
  HighwayDrive,
  VehicleFamily
}
import org.scalatest.funsuite.AnyFunSuite

class DiscountCalculatorTest extends AnyFunSuite {

  private val calculator = new DiscountCalculator

  test("朝夕平日割引 1回") {
    val driver = Driver(countPerMonth = 1)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2016, 3, 31, 23, 0),
      exitedAt = LocalDateTime.of(2016, 4, 1, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("朝夕平日割引 4回") {
    val driver = Driver(countPerMonth = 4)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2016, 3, 31, 23, 0),
      exitedAt = LocalDateTime.of(2016, 4, 1, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("朝夕平日割引 5回") {
    val driver = Driver(countPerMonth = 5)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2016, 3, 31, 23, 0),
      exitedAt = LocalDateTime.of(2016, 4, 1, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(30)

    assertResult(expected)(actual)
  }

  test("朝夕平日割引 10回") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2016, 3, 31, 23, 0),
      exitedAt = LocalDateTime.of(2016, 4, 1, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("休日朝夕は休日割引") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2016, 4, 1, 23, 0),
      exitedAt = LocalDateTime.of(2016, 4, 2, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(50)

    assertResult(expected)(actual)
  }

  test("深夜割引") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Other,
      areaType = AreaType.Urban,
      enteredAt = LocalDateTime.of(2016, 4, 1, 0, 0),
      exitedAt = LocalDateTime.of(2016, 4, 1, 4, 0)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(50)

    assertResult(expected)(actual)
  }
}
