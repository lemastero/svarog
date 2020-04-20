package svarog.preorders

import svarog.EquationalLaws

/**
 * Poset (partial order, skeletal preorders) is preorder (X, ≤) where skeletality holds.
 * - skeletality: forall x, y ∈ P we have x ~ y whenever x = y
 */
trait Poset[P] extends Preorder[P]

trait PosetLaws extends PreorderLaws {

  def skeletality[X](a: X, b: X)(implicit P: Preorder[X]): Boolean =
    EquationalLaws.skeletality(a,b,P.equivalent)
}
