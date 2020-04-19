package svarog.algebra

import simulacrum.typeclass

@typeclass
trait Monoid[X]
  extends Semigroup[X]
  with UnitalMagma[X]

trait MonoidLaws
  extends SemigroupLaws
  with UnitalMagmaLaws

object MonoidLaws
  extends MonoidLaws {}


