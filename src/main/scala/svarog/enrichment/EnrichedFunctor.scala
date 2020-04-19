package svarog.enrichment

import svarog.preorders.MonoidalPreorder
import svarog.preorders.Preorder.ops._

trait EnrichedFunctor[X,Y,V] {
  def map(
     v: EnrichedCategory[V,X],
     f: X => Y): EnrichedCategory[V,Y]
}

trait EnrichedFunctorLaws {

  def efLaw[X,Y,V](
      x: X, y: X,
      f: X => Y,
      v: EnrichedCategory[V,X], F: EnrichedFunctor[X,Y,V]): Boolean = {
    val vy: EnrichedCategory[V,Y] = F.map(v,f)
    implicit val b: MonoidalPreorder[V] = v.base
    v.homObject(x,y) <= vy.homObject(f(x), f(y))
  }
}

//object EnrichedFunctor {
//  type BoolFunctor[X,Y] = EnrichedFunctor[X,Y,Boolean]
//
//  def fromMonoidalMonotone[X,Y](fm: MonoidalMonotone[X,Y]): BoolFunctor[X,Y] =
//    new EnrichedFunctor[X,Y,Boolean] {
//      override def map(v: EnrichedCategory[Boolean, X], f: X => Y): EnrichedCategory[Boolean, Y] = {
//        new EnrichedCategory[Boolean, Y] {
//          override def base: MonoidalPreorder[Boolean] = fm.map(v.base)
//          override def homObject(x: Y, y: Y): Boolean = fm.apply()
//        }
//      }
//    }
//}
