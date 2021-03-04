package svarog.algebra

import simulacrum.typeclass
import svarog.universal_algebra.EquationalLaws

@typeclass
trait Semigroup[X] extends Magma[X]

/**
 * Laws for Semigroup:
 * - associativity: forall a,b,c ∈ X, (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c)
 */
trait SemigroupLaws {

  /** forall a,b,c ∈ X, (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c) */
  def associativity[X](a: X, b: X, c: X)(implicit P: Semigroup[X]): Boolean =
    EquationalLaws.associativity(a,b,c, P.multiply)
}

object SemigroupLaws extends SemigroupLaws {}

