package svarog.enrichment

import svarog.order_theory.MonoidalPreorder
import svarog.set_theory.MathSet

class OppositeEnrichedCategory[V,X](ec: EnrichedCategory[V,X])
  extends EnrichedCategory[V,X] {
  override def base: MonoidalPreorder[V] = ec.base
  override def objects: MathSet[X] = ec.objects
  override def homObject(x: X, y: X): V = ec.homObject(y,x)
}
