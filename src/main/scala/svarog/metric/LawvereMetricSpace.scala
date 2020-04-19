package svarog.metric

import simulacrum.typeclass
import svarog.data.PositiveRealsWithInfinity.{ZERO, `[0,oo]`}

@typeclass
trait LawvereMetricSpace[X]{
  def distance(a: X, b: X): `[0,oo]`
}

trait LawvereMetricSpaceLaws {
  import svarog.preorders.SymmetricMonoidalClosedPreorder.ops._
  import svarog.data.PositiveRealsWithInfinity.SMCP

  def msLaw1[X](x: X)(implicit ms: LawvereMetricSpace[X]): Boolean =
    ms.distance(x,x) == ZERO

  def trianglInequality[X](x: X, y: X, z: X)(implicit ms: LawvereMetricSpace[X]): Boolean =
    ms.distance(x,z) <= ms.distance(x,y) * ms.distance(y,z)
}

// TODO weight graph into Lawvere metric space
// TODO Hesse diagrams are like weighted graphs (we weight using Bool)
