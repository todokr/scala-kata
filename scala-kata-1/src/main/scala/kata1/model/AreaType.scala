package kata1.model

/** 高速道路のエリア */
sealed trait AreaType

object AreaType {

  /** 都市部（東京・大阪近郊） */
  case object Urban extends AreaType

  /** 地方部 */
  case object Rural extends AreaType
}
