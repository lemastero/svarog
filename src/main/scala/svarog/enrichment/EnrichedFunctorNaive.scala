//package svarog.enrichment
//
//import svarog.monotone.MonoidalMonotone
//
//abstract class EnrichedFunctorNaive[X,Y,V](
//   val v: EnrichedCategory[V,X],
//   val y: EnrichedCategory[V,Y]
//) {
// def F: X => Y
//}
//
//trait EnrichedFunctorNaiveLaws {
//  def efLaw[X,Y,V](x: X, y: X, v: EnrichedFunctorNaive[X,Y,X]): Boolean = {
//    v.v.base.le(
//      v.v.homObject(x,y),
//      v.y.homObject(v.F(x), v.F(y))
//    )
//  }
//}
//
//object EnrichedFunctorNaive {
//  type BoolFunctor[X,Y] = EnrichedFunctorNaive[X,Y,Boolean]
//
//  def fromMonoidalMonotone[X,Y](fm: MonoidalMonotone[X,Y]): BoolFunctor[X,Y] =
//    new EnrichedFunctorNaive[X,Y,Boolean](???) {
//      override def F: X => Y = fm
//    }
//}
