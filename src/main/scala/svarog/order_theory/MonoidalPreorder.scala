package svarog.order_theory

import simulacrum.typeclass
import svarog.algebra.{Monoid, MonoidLaws}
import svarog.universal_algebra.EquationalLaws

/**
 * Preorder with monoidal structure is a preorder (X, ≤) equipped with:
 * - monoidal product: operation ⊗ : X × X -> X
 * - monoidal unit: element I
 * such that:
 * - monotonicity: forall a1, a2, b1, b2 ∈ X, if a1 ≤ b1 and a2 ≤ b2, then a1 ⊗ a2 ≤ b1 ⊗ b2
 * - unitality: forall a ∈ X, I ⊗ a = a and a ⊗ I = a
 * - associativity: forall a,b,c ∈ X, (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c)
 */
@typeclass
trait MonoidalPreorder[X]
  extends Preorder[X]
  with Monoid[X]

object MonoidalPreorder {

  val `(N, <=, 1, |)`: MonoidalPreorder[Int] = new MonoidalPreorder[Int] { // TODO BigInt
    override def I: Int = 1
    override def multiply(a: Int, b: Int): Int = a * b
    override def le(a: Int, b: Int): Boolean = a % b == 0
  }
}

/**
 * Laws for MonoidalPreorder:
 * - monotonicity: forall a1, a2, b1, b2 ∈ X, if a1 ≤ b1 and a2 ≤ b2, then a1 ⊗ a2 ≤ b1 ⊗ b2
 * - unitality: forall a ∈ X, I ⊗ a = a and a ⊗ I = a
 * - associativity: forall a,b,c ∈ X, (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c)
 */
trait MonoidalPreorderLaws extends PreorderLaws with MonoidLaws {

  /** forall a1, a2, b1, b2 ∈ X, if a1 ≤ b1 and a2 ≤ b2, then a1 ⊗ a2 ≤ b1 ⊗ b2 */
  def monotonicity[X](a1: X, a2: X, b1: X, b2: X)(implicit P: MonoidalPreorder[X]): Boolean =
    EquationalLaws.monotonicity(a1,a2,b1,b2, P.multiply, P.le)
}

object MonoidalPreorderLaws extends MonoidalPreorderLaws {}
