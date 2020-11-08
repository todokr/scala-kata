package kata1.model

import java.time.LocalDateTime

/** 1ドライバーの、1回の高速道路の走行記録 */
case class HighwayDrive (
  driver: Driver,
  vehicleFamily: VehicleFamily,
  areaType: AreaType,
  enteredAt: LocalDateTime,
  exitedAt: LocalDateTime,
)


