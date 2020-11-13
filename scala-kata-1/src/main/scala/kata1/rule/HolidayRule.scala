package kata1.rule

import kata1.model.{AreaType, DiscountPercentage, HighwayDrive, VehicleFamily}
import kata1.util.HolidayUtils

/** 休日割引の適用ルール */
object HolidayRule extends DiscountRule {
  import VehicleFamily._

  // 割引を適用できる車種
  private val targetVehicleType: Set[VehicleFamily] =
    Set(Standard, Mini, Motorcycle)

  override private[rule] def isApplicable(drive: HighwayDrive) = {
    import HolidayUtils._
    val dayCondition =
      isHoliday(drive.enteredAt.toLocalDate) ||
        isHoliday(drive.exitedAt.toLocalDate)
    val vehicleCondition = targetVehicleType.contains(drive.vehicleFamily)
    val areaCondition = drive.areaType == AreaType.Rural
    dayCondition && vehicleCondition && areaCondition
  }

  override private[rule] def applyDiscount(drive: HighwayDrive) =
    DiscountPercentage(30)
}
