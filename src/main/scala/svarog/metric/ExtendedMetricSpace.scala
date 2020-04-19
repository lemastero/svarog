package svarog.metric

import simulacrum.typeclass
import svarog.data.PositiveRealsWithInfinity.ZERO

@typeclass
trait ExtendedMetricSpace[X]
  extends LawvereMetricSpace[X]

trait ExtendedMetricSpaceLaws
  extends LawvereMetricSpaceLaws {

  def symmetry[X](x: X, y: X)(implicit ms: MetricSpace[X]): Boolean =
    ms.distance(x,y) == ms.distance(y,x)

  def msLaw2[X](x: X, y: X)(implicit ms: ExtendedMetricSpace[X]): Boolean = {
    if( ms.distance(x,y) == ZERO ) x == y
    else true
  }
}
