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

trait MonoidLaws
  extends SemigroupLaws
  with UnitalMagmaLaws

object MonoidLaws
  extends MonoidLaws {}


