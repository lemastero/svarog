package svarog.algebra

import simulacrum.typeclass
import svarog.universal_algebra.EquationalLaws

@typeclass
trait UnitalMagma[X]
  extends Magma[X] {

  def I: X
}

/**
 * Laws for UnitalMagma:
 * - unitality: forall a ∈ X, I ⊗ a = a and a ⊗ I = a
 */
trait UnitalMagmaLaws {

  /** forall a ∈ X, I ⊗ a = a and a ⊗ I = a */
  def unitality[X](a: X)(implicit P: UnitalMagma[X]): Boolean =
    EquationalLaws.unitality(a, P.multiply, P.I)
}

object UnitalMagmaLaws extends UnitalMagmaLaws {}
