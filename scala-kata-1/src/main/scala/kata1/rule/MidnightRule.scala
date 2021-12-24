package kata1.rule

import kata1.model.{DiscountPercentage, DiscountPeriod, HighwayDrive}

/** 深夜割引の適用ルール */
object MidnightRule extends DiscountRule {

  private val period = DiscountPeriod(startHour = 0, endHour = 4)

  override def isApplicable(drive: HighwayDrive): Boolean = period.hasIntersection(drive)

  override def applicableDiscount(drive: HighwayDrive): DiscountPercentage = DiscountPercentage(30)
}
