package svarog.sets

trait MathSet[X] extends Function1[X,Boolean]

case class FiniteSet[X](repr: List[X]) extends MathSet[X] {
  override def apply(x: X): Boolean =
    repr.contains(x)
}

trait InfiniteSet[X] extends MathSet[X]

trait Subset[XA,X] extends MathSet[XA]
case class InfSubset[XA <: X,X](parent: MathSet[X], refinement: MathSet[XA]) extends MathSet[XA] {
  override def apply(x: XA): Boolean = parent(x) && refinement(x)
}
