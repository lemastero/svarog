package svarog.algebra

import simulacrum.typeclass

@typeclass
trait CoMonoid[X] extends CoSemigroup[X] {
  def discard: X => CI.type
  val CI: X
}

trait ComonoidLaws extends CoSemigroupLaws {

  def counitalityLeft[X](a: X)(implicit P: CoMonoid[X]): Boolean = {
    val lhs: X = P.duplicate(a) match {
      case (a1,a2) =>
        P.discard(a1)
        a2
    }

    lhs == a
  }

  def counitalityRight[X](a: X)(implicit P: CoMonoid[X]): Boolean = {
    val rhs: X = P.duplicate(a) match {
      case (a1,a2) =>
        P.discard(a2)
        a1
    }
    a == rhs
  }
}

object ComonoidLaws extends ComonoidLaws {}

