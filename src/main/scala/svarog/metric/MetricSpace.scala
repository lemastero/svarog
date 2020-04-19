package svarog.metric

trait MetricSpace[X] {
  def distance(a: X, b: X): BigDecimal // TODO ensure >= 0
}

trait MetricSpaceLaws {
  val zero = BigDecimal(0)

  def noDistanceFromPointToItself[X](x: X)(implicit ms: MetricSpace[X]): Boolean =
   ms.distance(x,x) == zero

  def symmetry[X](x: X, y: X)(implicit ms: MetricSpace[X]): Boolean =
    ms.distance(x,y) == ms.distance(y,x)

  def equalWhenNoDistance[X](x: X, y: X)(implicit ms: MetricSpace[X]): Boolean =
    if( ms.distance(x,y) == zero ) x == y
    else true

  def trianglInequality[X](x: X, y: X, z: X)(implicit ms: MetricSpace[X]): Boolean =
    ms.distance(x,y) + ms.distance(y,z) >= ms.distance(x,z)
}


object MetricSpace {
  val R: MetricSpace[Double] = (a: Double, b: Double) => Math.abs(b - a)
}