package svarog.set_theory

object MathSet {
  type MS[X] = MathSet[X]

  def intersect[X](a: MS[X], b: MS[X]): MS[X] = x => a(x) && b(x)
  def union[X](a: MS[X], b: MS[X]): MS[X] = x => a(x) || b(x)
  def symmetricDiff[X](a: MS[X], b: MS[X]): MS[X] = x =>
    (a(x) && not(b)(x)) || (not(a)(x) && b(x))
  def diff[X](a: MS[X], b: MS[X]): MS[X] = x => a(x) || not(b)(x)
  def not[X](s: MS[X]): MS[X] = x => ! s(x)
  def complement[X]: MS[X] => MS[X] = not
}

case class CartesianProduct[X,Y](x: MathSet[X], y: MathSet[Y])
  extends MathSet[(X,Y)] {

  override def apply(ab: (X, Y)): Boolean = x(ab._1) && y(ab._2)
}

trait MathSet[X] extends Function1[X,Boolean]

case class FiniteSet[X](repr: Set[X]) extends MathSet[X] {
  override def apply(x: X): Boolean =
    repr.contains(x)

  def powerSet: FiniteSet[X] = ??? // TODO
}

trait InfiniteSet[X] extends MathSet[X]

trait Subset[XA,X] extends MathSet[XA]
case class InfSubset[XA <: X,X](parent: MathSet[X], refinement: MathSet[XA])
  extends MathSet[XA] {

  override def apply(x: XA): Boolean = parent(x) && refinement(x)
}
