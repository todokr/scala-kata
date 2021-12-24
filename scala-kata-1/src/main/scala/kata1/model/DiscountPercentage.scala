package kata1.model

/** 割引率のパーセンテージ
  *
  * min: 0, max: 100 */
case class DiscountPercentage(value: Int)
    extends AnyVal
    with Ordered[DiscountPercentage] {

  override def compare(that: DiscountPercentage): Int = {
    if (this.value < that.value) -1
    else if (this.value > that.value) 1
    else 0
  }
}

object DiscountPercentage {

  val Zero: DiscountPercentage = DiscountPercentage(0)
}