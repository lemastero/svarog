package svarog.algebra

import simulacrum.typeclass

@typeclass
trait Monoid[X]
  extends Semigroup[X]
  with UnitalMagma[X]

object Monoid {
  def apply[X](e: X)(op: (X,X) => X): Monoid[X] = new Monoid[X] {
    override def I: X = e
    override def multiply(a: X, b: X): X = op(a,b)
  }
}

object MonoidInstances {
  val `(R, +, 0)` = Monoid[Double](0.0)(_ + _)
  val `(N, +, 0)` = Monoid[Int](0)(_ + _)
  val `(R, *, 1)` = Monoid[Double](1.0)(_ * _)
  val `(N, *, 1)` = Monoid[Int](1)(_ * _)
  val `(B, AND, true)` = Monoid[Boolean](true)(_ && _)
  val `(B, OR, false)` = Monoid[Boolean](false)(_ || _)
}

trait MonoidLaws
  extends SemigroupLaws
  with UnitalMagmaLaws

object MonoidLaws
  extends MonoidLaws {}


