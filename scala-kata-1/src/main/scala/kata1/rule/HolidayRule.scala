package kata1.rule

import kata1.model.{AreaType, DiscountPercentage, HighwayDrive, VehicleFamily}
import kata1.util.HolidayUtils
import HolidayUtils._

/** 休日割引の適用ルール */
object HolidayRule extends DiscountRule {
  import VehicleFamily._

  // 休日割引を適用できる車種
  private val targetVehicle: Set[VehicleFamily] =
    Set(Standard, Mini, Motorcycle)

  override def isApplicable(drive: HighwayDrive): Boolean =
    (isHoliday(drive.enteredAt.toLocalDate) || isHoliday(drive.exitedAt.toLocalDate)) &&
    targetVehicle.contains(drive.vehicleFamily) &&
    drive.areaType == AreaType.Rural

  override def applicableDiscount(drive: HighwayDrive): DiscountPercentage = {
    val percentage = drive.vehicleFamily match {
      case Mini | Motorcycle => 30
      case _                 => 20
    }
    DiscountPercentage(percentage)
  }
}
