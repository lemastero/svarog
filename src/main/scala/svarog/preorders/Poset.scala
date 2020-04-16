package svarog.preorders

import svarog.Equivalence.ops._

/**
 * Poset (partial order, skeletal preorders) is preorder (X, ≤) where skeletality holds.
 * - skeletality: forall p, q ∈ P we have q ≤ p whenever p ≤ q
 */
trait Poset[P] extends Preorder[P]

trait PosetLaws extends PreorderLaws {

  def skeletality[X](a: X, b: X)(implicit P: Preorder[X]): Boolean =
    if(a ~ b) a == b
    else true
}
