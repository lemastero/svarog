package svarog.preorders

import simulacrum.typeclass
import svarog.algebra.{Monoid, MonoidLaws}

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
trait MonoidalPreorder[X] extends Preorder[X] with Monoid[X]

/**
 * Laws for MonoidalPreorder:
 * - monotonicity: forall a1, a2, b1, b2 ∈ X, if a1 ≤ b1 and a2 ≤ b2, then a1 ⊗ a2 ≤ b1 ⊗ b2
 * - unitality: forall a ∈ X, I ⊗ a = a and a ⊗ I = a
 * - associativity: forall a,b,c ∈ X, (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c)
 */
trait MonoidalPreorderLaws extends PreorderLaws with MonoidLaws {
  import svarog.preorders.Preorder.ops._
  import svarog.algebra.Monoid.ops._

  /** forall a1, a2, b1, b2 ∈ X, if a1 ≤ b1 and a2 ≤ b2, then a1 ⊗ a2 ≤ b1 ⊗ b2 */
  def monotonicity[X](a1: X, a2: X, b1: X, b2: X)(implicit P: MonoidalPreorder[X]): Boolean =
    if( (a1 <= b1) && (a2 <= b2) ) (a1 * a2) <= (b1 * b2)
    else true
}

object MonoidalPreorderLaws extends MonoidalPreorderLaws
