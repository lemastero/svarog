package svarog.algebra

import simulacrum.typeclass
import svarog.universal_algebra.EquationalLaws

@typeclass
trait SymmetricMonoid[X]
  extends Monoid[X]

/**
 * Laws for SymmetricMonoidalPreorder:
 * - symmetry: forall a,b ∈ X, a ⊗ b = b ⊗ a
 */
trait SymmetricMonoidLaws
  extends MonoidLaws {

  /** forall a,b ∈ X, a ⊗ b = b ⊗ a */
  def symmetry[X](a: X, b: X)(implicit P: SymmetricMonoid[X]): Boolean =
    EquationalLaws.symmetry(a,b,P.multiply)
}

object SymmetricMonoid {
  def apply[X](e: X)(op: (X,X) => X): Monoid[X] = new Monoid[X] {
    override def I: X = e
    override def multiply(a: X, b: X): X = op(a,b)
  }

  val `(R, +, 0)` = SymmetricMonoid[Double](0.0)(_ + _)
  val `(N, +, 0)` = SymmetricMonoid[Int](0)(_ + _)
  val `(R, *, 1)` = SymmetricMonoid[Double](1.0)(_ * _)
  val `(N, *, 1)` = SymmetricMonoid[Int](1)(_ * _)
  val `(B, AND, true)` = SymmetricMonoid[Boolean](true)(_ && _)
  val `(B, OR, false)` = SymmetricMonoid[Boolean](false)(_ || _)
}
