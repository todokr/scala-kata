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

  test("割引なし: 平日 ~ 5:50") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 5, 30),
      exitedAt = LocalDateTime.of(2020, 11, 5, 5, 50)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }
  test("割引なし: 平日 9:10 ~ ") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 9, 10),
      exitedAt = LocalDateTime.of(2020, 11, 5, 9, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("割引なし: 平日 ~ 16:50") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 16, 30),
      exitedAt = LocalDateTime.of(2020, 11, 5, 16, 50)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }
  test("割引なし: 平日 20:10 ~ ") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 20, 10),
      exitedAt = LocalDateTime.of(2020, 11, 5, 20, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("朝夕平日割引: 平日 6:40 ~ 8:30") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 6, 40),
      exitedAt = LocalDateTime.of(2020, 11, 5, 8, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(50)

    assertResult(expected)(actual)
  }

  test("朝夕平日割引: 平日 5:30 ~ 8:00") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 5, 30),
      exitedAt = LocalDateTime.of(2020, 11, 5, 8, 0)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(50)

    assertResult(expected)(actual)
  }

  test("朝夕平日割引: 平日 7:00 ~ 9:10") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 7, 0),
      exitedAt = LocalDateTime.of(2020, 11, 5, 9, 10)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(50)

    assertResult(expected)(actual)
  }

  test("not 朝夕平日割引: 平日 5:30 ~ 9:10") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 5, 30),
      exitedAt = LocalDateTime.of(2020, 11, 5, 9, 10)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("朝夕平日割引 4回: 0%") {
    val driver = Driver(countPerMonth = 4)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 5, 30),
      exitedAt = LocalDateTime.of(2020, 11, 5, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(0)

    assertResult(expected)(actual)
  }

  test("朝夕平日割引 5回: 30%") {
    val driver = Driver(countPerMonth = 5)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 5, 30),
      exitedAt = LocalDateTime.of(2020, 11, 5, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(30)

    assertResult(expected)(actual)
  }

  test("朝夕平日割引 10回: 50%") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2020, 11, 5, 5, 30),
      exitedAt = LocalDateTime.of(2020, 11, 5, 6, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(50)

    assertResult(expected)(actual)
  }

  test("休日朝夕は休日割引") {
    val driver = Driver(countPerMonth = 10)
    val drive = HighwayDrive(
      driver = driver,
      vehicleFamily = VehicleFamily.Standard,
      areaType = AreaType.Rural,
      enteredAt = LocalDateTime.of(2016, 4, 1, 23, 0), // Fri
      exitedAt = LocalDateTime.of(2016, 4, 2, 6, 30) // Sat
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
      enteredAt = LocalDateTime.of(2016, 4, 1, 0, 10),
      exitedAt = LocalDateTime.of(2016, 4, 1, 4, 50)
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
      enteredAt = LocalDateTime.of(2016, 4, 1, 0, 10),
      exitedAt = LocalDateTime.of(2016, 4, 1, 6, 30)
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
      enteredAt = LocalDateTime.of(2016, 3, 31, 23, 30),
      exitedAt = LocalDateTime.of(2016, 4, 1, 0, 30)
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
      enteredAt = LocalDateTime.of(2016, 3, 31, 23, 30),
      exitedAt = LocalDateTime.of(2016, 4, 1, 5, 30)
    )

    val actual = calculator.calc(drive)
    val expected = DiscountPercentage(30)

    assertResult(expected)(actual)
  }
}
