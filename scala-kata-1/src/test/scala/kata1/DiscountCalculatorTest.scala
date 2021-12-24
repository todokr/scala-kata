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

  test("割引なし: 平日 ~ 5:59") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 24, 5, 30),
      exitedAt = LocalDateTime.of(2021, 12, 24, 5, 59)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }
  test("割引なし: 平日 9:01 ~ ") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 24, 9, 1),
      exitedAt = LocalDateTime.of(2021, 12, 24, 9, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("割引なし: 平日 ~ 16:59") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 24, 16, 30),
      exitedAt = LocalDateTime.of(2021, 12, 24, 16, 59)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }
  test("割引なし: 平日 20:01 ~ ") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 24, 20, 1),
      exitedAt = LocalDateTime.of(2021, 12, 24, 20, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("平日朝割引: 平日 6:00 ~ ") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 24, 6, 0),
      exitedAt = LocalDateTime.of(2021, 12, 24, 8, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(50)

    assertResult(expected)(actual)
  }

  test("平日朝割引: 平日 ~ 9:00") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 24, 5, 30),
      exitedAt = LocalDateTime.of(2021, 12, 24, 9, 0),
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(50)

    assertResult(expected)(actual)
  }

  test("not 平日朝割引: 平日 5:59 ~ 9:01") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 24, 5, 59),
      exitedAt = LocalDateTime.of(2021, 12, 24, 9, 1),
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("平日朝割引 4回: 0%") {
    val driver = Driver(countPerMonth = 4)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 24, 5, 30),
      exitedAt = LocalDateTime.of(2021, 12, 24, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("平日朝割引 5回: 30%") {
    val driver = Driver(countPerMonth = 5)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 24, 5, 30),
      exitedAt = LocalDateTime.of(2021, 12, 24, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(30)

    assertResult(expected)(actual)
  }

  test("平日朝割引 10回: 50%") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 24, 5, 30),
      exitedAt = LocalDateTime.of(2021, 12, 24, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(50)

    assertResult(expected)(actual)
  }

  test("休日朝は休日割引") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2021, 12, 25, 5, 30), // Sat
      exitedAt = LocalDateTime.of(2021, 12, 25, 6, 30) // Sat
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(30)

    assertResult(expected)(actual)
  }

  test("深夜割引: 走行 in 時間帯") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Other,
      areaType = AreaType.Urban,
      enteredAt = LocalDateTime.of(2021, 12, 24, 0, 10),
      exitedAt = LocalDateTime.of(2021, 12, 24, 3, 50)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(30)

    assertResult(expected)(actual)
  }

  test("深夜割引: 時間帯 contains 走行開始") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Other,
      areaType = AreaType.Urban,
      enteredAt = LocalDateTime.of(2021, 12, 24, 4, 0),
      exitedAt = LocalDateTime.of(2021, 12, 24, 4, 1)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(30)

    assertResult(expected)(actual)
  }

  test("深夜割引: 時間帯 contains 走行終了") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Other,
      areaType = AreaType.Urban,
      enteredAt = LocalDateTime.of(2021, 12, 23, 23, 59),
      exitedAt = LocalDateTime.of(2021, 12, 24, 0, 0)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(30)

    assertResult(expected)(actual)
  }

  test("深夜割引: 走行 contains 時間帯") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Other,
      areaType = AreaType.Urban,
      enteredAt = LocalDateTime.of(2021, 12, 23, 23, 59),
      exitedAt = LocalDateTime.of(2021, 12, 24, 4, 1)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(30)

    assertResult(expected)(actual)
  }
}