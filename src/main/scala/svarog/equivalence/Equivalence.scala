package svarog.equivalence

import simulacrum.{op, typeclass}
import svarog.universal_algebra.EquationalLaws

/**
 * Equivalence relation ~ is a binary operation that is:
 * - (reflexivity) forall a∈A, a ~ a
 * - (symmetry) forall a,b∈A, a ~ b <=> b ~ a
 * - (transitivity) forall a,b,c∈A if a ~ b and b ~ c then a ~ c
 */
@typeclass
trait Equivalence[A] { // consider just using scala.Equiv without simulacrum magic

  @op("~")
  def equivalent(a: A, b: A): Boolean
}

trait EquivalenceLaws {

  def reflexivity[A](a: A)(implicit E: Equivalence[A]): Boolean =
    EquationalLaws.reflexivity(a, E.equivalent)

  def symmetry[A](a: A, b: A)(implicit E: Equivalence[A]): Boolean =
    EquationalLaws.symmetry(a,b,E.equivalent)

  def transitivity[A](a: A, b: A, c: A)(implicit E: Equivalence[A]): Boolean =
    EquationalLaws.transitivity(a,b,c,E.equivalent)
}
