package kata1

import kata1.model.{DiscountPercentage, HighwayDrive}
import kata1.rule._

/** 高速道路の走行記録から割引率を計算する */
class DiscountCalculator {
  import DiscountCalculator.rules

  def calc(drive: HighwayDrive): DiscountPercentage =
    rules.map(_.discountPercentage(drive)).max
}

object DiscountCalculator {
  private val rules: Set[DiscountRule] =
    Set(MorningOfWeekdayRule, HolidayRule, MidnightRule)
}
