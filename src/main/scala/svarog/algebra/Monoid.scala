package svarog.algebra

import simulacrum.typeclass

@typeclass
trait Monoid[X]
  extends Semigroup[X]
  with UnitalMagma[X]

object Monoid {
  def apply[X](e: X)(op: (X,X) => X): Monoid[X] = new Monoid[X] {
    override def I: X = e
    override def multiply(a: X, b: X): X = op(a,b)
  }
}

/**
 * Laws for Monoid:
 * - associativity: forall a,b,c ∈ X, (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c)
 * - unitality: forall a ∈ X, I ⊗ a = a and a ⊗ I = a
 */
trait MonoidLaws
  extends SemigroupLaws
  with UnitalMagmaLaws

object MonoidLaws
  extends MonoidLaws {}


