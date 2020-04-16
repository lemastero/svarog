package svarog

import simulacrum.{op, typeclass}

/**
 * Equivalence relation ~ is a binary operation that is:
 * - (reflexivity) forall a∈A, a ~ a
 * - (symmetry) forall a,b∈A, a ~ b <=> b ~ a
 * - (transitivity) forall a,b,c∈A if a ~ b and b ~ c then a ~ c
 */
@typeclass
trait Equivalence[A] {
  @op("~")
  def equivalent(a: A, b: A): Boolean
}

trait EquivalenceLaws {
  import Equivalence.ops._

  def reflexivity[A](a: A)(implicit E: Equivalence[A]): Boolean =
    a ~ a

  def symmetry[A](a: A, b: A)(implicit E: Equivalence[A]): Boolean =
    if(a ~ b) b ~ a
    else if(b ~ a) a ~ b
    else true

  def transitivity[A](a: A, b: A, c: A)(implicit E: Equivalence[A]): Boolean =
    if ((a ~ b) && (b ~ c)) b ~ c
    else true
}
