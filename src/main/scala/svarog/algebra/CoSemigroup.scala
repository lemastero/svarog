package svarog.algebra

import simulacrum.{op, typeclass}
import svarog.universal_algebra.EquationalLaws

@typeclass
trait CoSemigroup[X] {
  @op("*") def duplicate(a: X): (X,X)
}

trait CoSemigroupLaws {

  def coassociativity[X](a: X)(implicit P: CoSemigroup[X]): Boolean = {
    val a1a2 = P.duplicate(a)
    val lhs: (X, X, X) = a1a2 match { case(a1,a2) => P.duplicate(a1) match {
        case (a1a,a1b) => (a1a, a1b, a2)
      }
    }
    val rhs: (X, X, X) = a1a2 match { case(a1,a2) => P.duplicate(a2) match {
        case (a2a,a2b) => (a1, a2a, a2b)
      }
    }
    lhs == rhs
  }
}

object CoSemigroupLaws extends UnitalMagmaLaws {}
