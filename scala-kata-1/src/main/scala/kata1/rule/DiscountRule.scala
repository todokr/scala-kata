package kata1.rule

import kata1.model.{DiscountPercentage, HighwayDrive}

/** ETC割引のルール */
trait DiscountRule {

  /** 走行記録に対して適用できる割引率を計算する **/
  def discountPercentage(drive: HighwayDrive): DiscountPercentage =
    if (isApplicable(drive)) applicableDiscount(drive) else DiscountPercentage.Zero

  /** 走行記録に対して割引が適用可能か  */
  protected def isApplicable(drive: HighwayDrive): Boolean

  /** 割引が適用可能な際の割引率はいくらか */
  protected def applicableDiscount(drive: HighwayDrive): DiscountPercentage
}
