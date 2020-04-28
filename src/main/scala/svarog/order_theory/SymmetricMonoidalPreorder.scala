package svarog.order_theory

import simulacrum.typeclass
import svarog.algebra.{SymmetricMonoid, SymmetricMonoidLaws}

/**
 * Preorder with monoidal structure (X, ≤, ⊗, I) is a preorder (X, ≤) equipped with:
 * - monoidal product: operation ⊗ : X × X -> X
 * - monoidal unit: element I
 * such that:
 * - monotonicity: forall a1, a2, b1, b2 ∈ X, if a1 ≤ b1 and a2 ≤ b2, then a1 ⊗ a2 ≤ b1 ⊗ b2
 * - unitality: forall a ∈ X, I ⊗ a = a and a ⊗ I = a
 * - associativity: forall a,b,c ∈ X, (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c)
 * - symmetry: forall a,b ∈ X, a ⊗ b􏰆 = b ⊗ a
 */
@typeclass
trait SymmetricMonoidalPreorder[X]
  extends MonoidalPreorder[X]
  with SymmetricMonoid[X]

/**
 * Laws for SymmetricMonoidalPreorder:
 * - symmetry: forall a,b ∈ X, a ⊗ b􏰆 = b ⊗ a
 */
trait SymmetricMonoidalPreorderLaws
  extends MonoidalPreorderLaws
    with SymmetricMonoidLaws

object SymmetricMonoidalPreorderLaws
  extends SymmetricMonoidalPreorderLaws {}

object SymmetricMonoidalPreorder {
  type SMP[X] = SymmetricMonoidalPreorder[X]

  def newSMP[X](lessThan: (X,X) => Boolean, e: X, m: (X,X) => X): SymmetricMonoidalPreorder[X] =
    new SymmetricMonoidalPreorder[X] {
      override def I: X = e
      override def multiply(a: X, b: X): X = m(a,b)
      override def le(a: X, b: X): Boolean = lessThan(a,b)
    }

  def newSMP[X](lessThan: Preorder[X], e: X, m: (X,X) => X): SymmetricMonoidalPreorder[X] =
    new SymmetricMonoidalPreorder[X] {
      override def I: X = e
      override def multiply(a: X, b: X): X = m(a,b)
      override def le(a: X, b: X): Boolean = lessThan.le(a,b)
    }

  val `(R, <=, 0, +)`: SMP[Double] =       newSMP[Double](_ <= _, 0, _ + _)
  val SMPBigIntPlusLePlus: SMP[BigInt] =   newSMP[BigInt](_ <= _, BigInt(0), _ + _)
  val SMPBigIntMultiGeTimes: SMP[BigInt] = newSMP[BigInt](_ >= _, BigInt(1), _ * _)
  val SMPBigIntMultiGePlus: SMP[BigInt] =  newSMP[BigInt](_ >= _, BigInt(0), _ + _)
  val `(B, ≤, true, ∧)` : SMP[Boolean] =   newSMP[Boolean](_ <= _, true, _ && _)
  val `(B, ≤, false, ∨)`: SMP[Boolean] =   newSMP[Boolean](_ <= _, false, _ || _)
}
