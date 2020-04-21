package svarog.functions

import svarog.metric.MetricSpace
import svarog.metric.MetricSpace.ops._

trait MetricPreserving[X]
  extends Function1[X,X]

trait MetricPreservingLaws {

  def preserveOrder[X](f: MetricPreserving[X], x: X, y: X)(implicit N: MetricSpace[X]): Boolean =
    (x |-| y) == ( f(x) |-| f(y) )
}

object MetricPreservingLaws
  extends MetricPreservingLaws
