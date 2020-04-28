package svarog.metric

import simulacrum.{op, typeclass}
import svarog.equivalence.Equivalence
import svarog.universal_algebra.EquationalLaws

@typeclass
trait MetricSpace[X]
  extends Equivalence[X] {

  @op("|-|")
  def distance(a: X, b: X): BigDecimal // TODO ensure >= 0

  val zero = BigDecimal(0)
  override def equivalent(a: X, b: X): Boolean =
    distance(a,b) == zero
}

trait MetricSpaceLaws {

  def noDistanceFromPointToItself[X](x: X)(implicit ms: MetricSpace[X]): Boolean =
    EquationalLaws.reflexivity(x,ms.equivalent)

  def symmetry[X](x: X, y: X)(implicit ms: MetricSpace[X]): Boolean =
    EquationalLaws.symmetry(x,y, ms.distance)

  def equalWhenNoDistance[X](x: X, y: X)(implicit ms: MetricSpace[X]): Boolean =
    EquationalLaws.skeletality(x,y,ms.equivalent)

  def triangleInequality[X](x: X, y: X, z: X)(implicit ms: MetricSpace[X]): Boolean =
    EquationalLaws.triangleInequality[X,BigDecimal](x,y,z,ms.distance, _ <= _, _ + _)
}


object MetricSpace {
  val R: MetricSpace[Double] = (a: Double, b: Double) => Math.abs(b - a)
}