package kata1.model

import java.time.{LocalDate, LocalTime}

import kata1.util.HolidayUtils

/** 割引適用の条件となる時間帯 */
case class DiscountPeriod(startHour: Int, endHour: Int) {
  val startTime: LocalTime = LocalTime.of(startHour, 0)
  val endTime: LocalTime = LocalTime.of(endHour, 0)

  /** 走行記録が時間帯に重なるか
    *
    * 走行記録と時間帯が同一の場合も重なっているとみなす */
  def hasIntersection(drive: HighwayDrive): Boolean = {
    // 当日の割引時間帯を過ぎてからの走行開始の場合、翌日の割引対象時間帯に重なるかを判定する
    val offset: Long =
      if (drive.enteredAt.toLocalTime.isAfter(endTime)) 1 else 0
    val discountStart =
      drive.enteredAt.toLocalDate.plusDays(offset).atTime(startTime)
    val discountEnd =
      drive.exitedAt.toLocalDate.plusDays(offset).atTime(endTime)

    // 2つの期間 A〜B と X〜Y が重なっているかどうかを判定したい場合、
    // X < B && Y > A
    // See: http://koseki.hatenablog.com/entry/20111021/range
    // isAfter/isBeforeは閉区間なので、ド・モルガンの法則で変換
    !(drive.enteredAt.isAfter(discountEnd) ||
      drive.exitedAt.isBefore(discountStart))
  }

  /** 時間帯中に料金所を通過したか */
  def isPassedThroughBy(drive: HighwayDrive): Boolean = {
    val enterTime = drive.enteredAt.toLocalTime
    val enterCondition =
      !(startTime.isAfter(enterTime) || endTime.isBefore(enterTime))
    val exitTime = drive.exitedAt.toLocalTime
    val exitCondition =
      !(startTime.isAfter(exitTime) || endTime.isBefore(exitTime))
    enterCondition || exitCondition
  }

  def isHoliday(drive: HighwayDrive): Boolean = {
    val offset: Long =
      if (drive.enteredAt.toLocalTime.isBefore(endTime)) 0 else 1
    HolidayUtils.isHoliday(LocalDate.from(drive.enteredAt.plusDays(offset)))
  }
}
