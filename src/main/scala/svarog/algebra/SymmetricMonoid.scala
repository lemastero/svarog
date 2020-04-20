package svarog.algebra

import simulacrum.typeclass
import svarog.EquationalLaws

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
