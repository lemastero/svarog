package svarog.preorders

/**
 * Poset (partial order, skeletal preorders) is preorder (X, ≤) where skeletality holds.
 * - skeletality: forall p, q ∈ P we have q ≤ p whenever p ≤ q
 */
trait Poset[P] extends Preorder[P]

trait PosetLaws extends PreorderLaws {

  def skeletality[X](a: X, b: X)(implicit P: Preorder[X]): Boolean =
    if(P.eqivalent(a,b)) a == b
    else false
}
