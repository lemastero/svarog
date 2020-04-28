package svarog.equivalence

import svarog.set_theory.MathSet
import Equivalence.ops._

trait EquivalenceClosed[X]
  extends MathSet[X]
    with Equivalence[X]

trait EquivalenceClosedLaws {

  def lawEC[A](a: A, b: A)(implicit E: EquivalenceClosed[A]): Boolean =
    if ( E(a) && (b ~ a) ) E(b)
    else if ( E(b) && (a ~ b) ) E(a)
    else true
}
