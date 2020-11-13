package kata1.rule

import kata1.model.{AreaType, DiscountPercentage, DiscountPeriod, HighwayDrive}

/** 平日朝夕割引の適用ルール */
object MorningOrEveningOfWeekdayRule extends DiscountRule {

  private val morningPeriod = DiscountPeriod(startHour = 6, endHour = 9)
  private val eveningPeriod = DiscountPeriod(startHour = 17, endHour = 20)

  override private[rule] def isApplicable(drive: HighwayDrive): Boolean = {
    val dayCondition =
      !(morningPeriod.isHoliday(drive) || eveningPeriod.isHoliday(drive))
    val timeCondition =
      morningPeriod.isPassedThroughBy(drive) ||
        eveningPeriod.isPassedThroughBy(drive)
    val areaCondition = drive.areaType == AreaType.Rural

    dayCondition && timeCondition && areaCondition
  }

  override private[rule] def applyDiscount(
    drive: HighwayDrive
  ): DiscountPercentage = {
    val percentage = drive.driver.countPerMonth match {
      case i if i >= 0 && i < 5  => 0
      case i if i >= 5 && i < 10 => 30
      case i if i >= 10          => 50
      case i                     => throw new Exception(s"countPerMonth=$i")
    }
    DiscountPercentage(percentage)
  }
}
