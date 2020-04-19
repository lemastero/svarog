package svarog.enrichment

import svarog.monotone.MonoidalMonotone
import svarog.preorders.MonoidalPreorder
import svarog.sets.MathSet

class BaseChange {

  def baseChange[V, W, X](
     f: MonoidalMonotone[V, W],
     ex: EnrichedCategory[V, X] // according to math definition I don't need this
   ): EnrichedCategory[W, X] =
    new EnrichedCategory[W, X] {
      override def base: MonoidalPreorder[W] = f.map(ex.base)
      override def objects: MathSet[X] = ex.objects
      override def homObject(x: X, y: X): W = f(ex.homObject(x, y))
    }
}
