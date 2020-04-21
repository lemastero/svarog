package svarog.enrichment

import svarog.preorders.MonoidalPreorder
import svarog.sets.MathSet

case class EnrichedProduct[V,X,Y](
  x: EnrichedCategory[V,X],
  y: EnrichedCategory[V,Y]
) extends EnrichedCategory[V,(X,Y)] {
  override def base: MonoidalPreorder[V] = x.base
  override def objects: MathSet[(X,Y)] = Function.const(true)
  override def homObject(a: (X,Y), b: (X,Y)): V =
    base.multiply(
      x.homObject(a._1,b._1),
      y.homObject(a._2,b._2)
    )
}
