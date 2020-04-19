package svarog.algebra

import simulacrum.typeclass

@typeclass
trait UnitalMagma[X]
  extends Magma[X] {

  def I: X
}

trait UnitalMagmaLaws {

  /** forall a ∈ X, I ⊗ a􏰆 = a and a ⊗ I = 􏰆a */
  def unitality[X](a: X)(implicit P: UnitalMagma[X]): Boolean = {
    import UnitalMagma.ops._
    import P.I
    ((I * a) == a) && (a == (a * I))
  }
}

object UnitalMagmaLaws extends UnitalMagmaLaws {}
