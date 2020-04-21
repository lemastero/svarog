package svarog.functions

import svarog.metric.LawvereMetricSpace.ops._
import svarog.metric.LawvereMetricSpace

trait LawvereMetricPreserving[X]
  extends Function1[X,X]

trait LawvereMetricPreservingLaws {

  def preserveMetric[X](f: MetricPreserving[X], x: X, y: X)(implicit N: LawvereMetricSpace[X]): Boolean =
    (x |-| y) == ( f(x) |-| f(y) )
}

object LawvereMetricPreservingLaws
  extends LawvereMetricPreservingLaws
