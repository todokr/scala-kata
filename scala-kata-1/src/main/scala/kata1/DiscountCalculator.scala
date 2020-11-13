package kata1

import kata1.model.{DiscountPercentage, HighwayDrive}
import kata1.rule._

/** 高速道路の走行記録から割引率を計算する */
class DiscountCalculator {

  private val rules: Seq[DiscountRule] =
    Seq(MorningOrEveningOfWeekdayRule, HolidayRule, MidnightRule)

  def calc(drive: HighwayDrive): DiscountPercentage =
    rules
      .map(rule => rule.apply(drive))
      .collect { case Some(discount) => discount }
      .maxOption
      .getOrElse(DiscountPercentage(0))
}
