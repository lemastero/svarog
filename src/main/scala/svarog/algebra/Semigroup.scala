package svarog.algebra

import simulacrum.typeclass

@typeclass
trait Semigroup[X] extends Magma[X]

trait SemigroupLaws {

  /** forall a,b,c ∈ X, (a ⊗ b) ⊗ c􏰆 = a ⊗ (b ⊗ c) */
  def associativity[X](a: X, b: X, c: X)(implicit P: Monoid[X]): Boolean = {
    import Monoid.ops._
    ((a * b) * c) == (a * (b * c))
  }
}

object SemigroupLaws extends SemigroupLaws {}

