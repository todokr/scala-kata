package kata1.rule

import kata1.model.{AreaType, DiscountPercentage, DiscountPeriod, HighwayDrive}

/** 平日朝割引の適用ルール */
object MorningOfWeekdayRule extends DiscountRule {

  private val period = DiscountPeriod(startHour = 6, endHour = 9)

  override def isApplicable(drive: HighwayDrive): Boolean =
    (!period.isHoliday(drive)) &&
      period.isPassedThroughBy(drive) &&
      drive.areaType == AreaType.Rural

  override def applicableDiscount(drive: HighwayDrive): DiscountPercentage = {
    val percentage = drive.driver.countPerMonth match {
      case i if i >= 10 => 50
      case i if i >= 5  => 30
      case _            => 0
    }
    DiscountPercentage(percentage)
  }
}
