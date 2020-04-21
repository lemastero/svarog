package svarog.equivalence

import svarog.Equivalence
import svarog.sets.MathSet
import svarog.Equivalence.ops._

trait EquivalenceConnected[X]
  extends MathSet[X]
    with Equivalence[X]

trait EquivalenceConnectedLaws {

  def lawEC[A](a: A, b: A)(implicit E: EquivalenceConnected[A]): Boolean =
    if ( E(a) && E(b) ) (b ~ a) && (a ~ b)
    else true
}
