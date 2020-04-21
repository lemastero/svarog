package svarog.enrichment

import svarog.metric.LawvereMetricSpace
import svarog.sets.PositiveRealsWithInfinity.`[0,oo]`
import svarog.preorders.SymmetricMonoidalPreorder
import svarog.sets.PositiveRealsWithInfinity

object CostCategory {
  type CostCategory[X] = EnrichedCategory[`[0,oo]`, X]

  def apply[X](lawvereMetric: LawvereMetricSpace[X]): CostCategory[X] = {
    new EnrichedCategory[`[0,oo]`, X] {
      override def base: SymmetricMonoidalPreorder[`[0,oo]`] = PositiveRealsWithInfinity.`([0, ∞], ≥, 0, +)`
      override def homObject(x: X, y: X): `[0,oo]` = lawvereMetric.distance(x,y)
    }
  }

  implicit def asLawvereMetric[X](implicit bc: CostCategory[X]): LawvereMetricSpace[X] =
    (a: X, b: X) => bc.homObject(a,b)
}
