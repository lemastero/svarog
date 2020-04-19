package svarog.data

import svarog.metric.ExtendedMetricSpace
import svarog.preorders.SymmetricMonoidalClosedPreorder
import svarog.preorders.SymmetricMonoidalClosedPreorder.MonoidalClosed

object PositiveRealsWithInfinity {
  sealed trait `[0,oo]`
  case object Inf extends `[0,oo]`
  case class R(v: BigDecimal) extends `[0,oo]`
    // TODO refinement types ensure >= 0
    // TODO fix numerical overflow

    val ZEROBD: BigDecimal = BigDecimal(0)
    val ZERO: `[0,oo]` = R(ZEROBD)

    def TIMES(a: `[0,oo]`, b: `[0,oo]`): `[0,oo]` = (a,b) match {
      case (R(a), R(b)) => R(a * b)
      case (_,    _)    => Inf
    }

    def PLUS(a: `[0,oo]`, b: `[0,oo]`): `[0,oo]` = (a,b) match {
      case (R(a), R(b)) => R(a + b)
      case (_,    _)    => Inf
    }

    def LE(a: `[0,oo]`, b: `[0,oo]`): Boolean =
      (a,b) match {
        case (Inf,  Inf)  => true
        case (Inf,  _)    => false
        case (_,    Inf)  => true
        case (R(a), R(b)) => a <= b
      }

    def GE(a: `[0,oo]`, b: `[0,oo]`): Boolean =
      (a,b) match {
        case (Inf,  Inf)  => true
        case (Inf,  _)    => true
        case (_,    Inf)  => false
        case (R(a), R(b)) => a >= b
      }

    // max(0, b - a)
    def MONUS(a: `[0,oo]`, b: `[0,oo]`): `[0,oo]` = (a,b) match {
      case (Inf, Inf)   => Inf  // TODO max(0, oo - oo) could be 0 or Inf
      case (_,   Inf)   => Inf
      case (Inf, _)     => ZERO
      case (R(a), R(b)) => R(ZEROBD.min(b - a)) // monus
    }

  implicit val SMCP: MonoidalClosed[`[0,oo]`] =
    SymmetricMonoidalClosedPreorder[`[0,oo]`](LE _, ZERO, PLUS _, MONUS _)
  val SMCP2: MonoidalClosed[`[0,oo]`] =
    SymmetricMonoidalClosedPreorder[`[0,oo]`](GE _, ZERO, TIMES _, MONUS _)

  def EMS: ExtendedMetricSpace[`[0,oo]`] =
    (a: `[0,oo]`, b: `[0,oo]`) => (a, b) match {
      case (R(a), R(b)) => R(if (a < b) b - a else a - b)
      case (_,    _) => Inf
    }
}
