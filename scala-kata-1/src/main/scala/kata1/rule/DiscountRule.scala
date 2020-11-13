package kata1.rule

import kata1.model.{DiscountPercentage, HighwayDrive}

/** ETC割引のルール */
trait DiscountRule {

  /** 走行記録に対して割引を適用する
    *
    * 適用できる場合は割引率をSomeで、適用できない場合はNoneで返す */
  def apply(drive: HighwayDrive): Option[DiscountPercentage] =
    if (isApplicable(drive)) Some(applyDiscount(drive)) else None

  /** 走行記録に対して割引が適用可能か  */
  private[rule] def isApplicable(drive: HighwayDrive): Boolean

  /** 割引が適用可能な際の割引率  */
  private[rule] def applyDiscount(drive: HighwayDrive): DiscountPercentage
}
