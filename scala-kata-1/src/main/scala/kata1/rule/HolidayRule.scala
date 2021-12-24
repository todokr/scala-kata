package kata1.rule

import kata1.model.{AreaType, DiscountPercentage, HighwayDrive, VehicleFamily}
import kata1.util.HolidayUtils
import HolidayUtils._

/** 休日割引の適用ルール */
object HolidayRule extends DiscountRule {
  import VehicleFamily._

  // 割引を適用できる車種
  private val targetVehicleType: Set[VehicleFamily] =
    Set(Standard, Mini, Motorcycle)

  override def isApplicable(drive: HighwayDrive): Boolean = {
    val dayCondition = isHoliday(drive.enteredAt.toLocalDate) || isHoliday(drive.exitedAt.toLocalDate)
    val isDiscountVehicle = targetVehicleType.contains(drive.vehicleFamily)
    val isDiscountArea = drive.areaType == AreaType.Rural
    dayCondition && isDiscountVehicle && isDiscountArea
  }

  override def applicableDiscount(drive: HighwayDrive): DiscountPercentage =
    DiscountPercentage(30)
}
