package svarog.metric

import simulacrum.{op, typeclass}
import svarog.EquationalLaws
import svarog.sets.PositiveRealsWithInfinity.{ZERO, `[0,oo]`}
import svarog.sets.PositiveRealsWithInfinity.`([0, ∞], ≥, 0, +)`

@typeclass
trait LawvereMetricSpace[X]{
  @op("|-|")
  def distance(a: X, b: X): `[0,oo]`

  def noEffort(a: X, b: X): Boolean =
    distance(a,b) == ZERO
}

trait LawvereMetricSpaceLaws {

  def zeroDistanceReflexivity[X](x: X)(implicit ms: LawvereMetricSpace[X]): Boolean =
    EquationalLaws.reflexivity(x,ms.noEffort)

  def trianglInequality[X](x: X, y: X, z: X)(implicit ms: LawvereMetricSpace[X]): Boolean =
    EquationalLaws.triangleInequality(x,y,z,ms.distance,`([0, ∞], ≥, 0, +)`.le,`([0, ∞], ≥, 0, +)`.multiply) // TODO check if SMCP def alight with this law!
}

// TODO weight graph into Lawvere metric space
// TODO Hesse diagrams are like weighted graphs (we weight using Bool)
