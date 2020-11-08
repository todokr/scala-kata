package kata1.model

/** 車種 */
sealed trait VehicleFamily

object VehicleFamily {

  /** 普通車 */
  case object Standard extends VehicleFamily

  /** 軽自動車 */
  case object Mini extends VehicleFamily

  /** 二輪車 */
  case object Motorcycle extends VehicleFamily

  /** その他 */
  case object Other extends VehicleFamily
}
