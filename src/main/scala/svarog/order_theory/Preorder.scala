package svarog.order_theory

import simulacrum.{op, typeclass}
import svarog.equivalence.Equivalence
import svarog.universal_algebra.EquationalLaws

/**
 * Preorder is a (X, ≤) set X equipped with binary relation ≤
 * that is reflexive and transitive.
 */
@typeclass
trait Preorder[X] extends Equivalence[X] { self =>
  // TODO explore designining Preorders with explicit set
  // this allows to express thing based on subsets - Meet, Join, Powerset, GaloisConnection
  // def set: MathSet[X]

  @op("<=") def le(a: X, b: X): Boolean

  // x and y are equivalent if y ≤ x and x ≤ y
  override def equivalent(a: X, b: X): Boolean = le(a,b) && le(b,a)
}

object Preorder {
  def apply[X](lessOrEqual: (X, X) => Boolean): Preorder[X] =
    (a: X, b: X) => lessOrEqual(a, b)

  val `(R, <=)`: Preorder[Double] =
    (a: Double, b: Double) => a <= b

  val `(B, <=)`: Preorder[Boolean] =
    (a: Boolean, b: Boolean) => a <= b

  def discretePreorder[X]: Preorder[X] =
    (a: X, b: X) => a == b
}

/**
 * Laws for preorder:
 * - reflexivity: forall a ∈ X, a ≤ a
 * - transitivity: forall a,b,c ∈ X if a ≤ b and b ≤ c then a ≤ c
 */
trait PreorderLaws {
  /** forall a ∈ X, a ≤ a */
  def reflexivity[X](a: X)(implicit P: Preorder[X]): Boolean =
    EquationalLaws.reflexivity[X](a, P.le)

  /** forall a,b,c ∈ X if a ≤ b and b ≤ c then a ≤ c */
  def transitivity[X](a: X, b: X, c: X)(implicit P: Preorder[X]): Boolean =
    EquationalLaws.transitivity(a,b,c,P.le)
}

object PreorderLaws extends PreorderLaws
