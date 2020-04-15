package svarog.preorders

import svarog.Equivalence

/**
 * Dagger preorder is a (X, ≤) is preorder where
 * forall p, q ∈ P we have q ≤ p whenever p ≤ q
 */
trait DaggerPreorder[P]
    extends Preorder[P]
    with Equivalence[P] {

  // dagger preorder is equivalence relation
  override def eqivalent(a: P, b: P): Boolean = le(a,b)
}

/**
 * Laws for dagger preorder :
 * - forall p, q ∈ P we have q ≤ p whenever p ≤ q
 */
trait DaggerPreorderLaws extends PreorderLaws {

  /** forall a,b,c ∈ X if a ≤ b and b ≤ c then a ≤ c */
  def symmetry[X](a: X, b: X)(implicit P: Preorder[X]): Boolean =
    if(P.le(a,b)) P.le(b,a)
    else if(P.le(b,a)) P.le(a,b)
    else false
}
