package svarog.preorders


/**
 * Dagger preorder is a (X, ≤) is preorder where
 * forall p, q ∈ P we have q ≤ p whenever p ≤ q
 */
trait DaggerPreorder[P]
    extends Preorder[P] {

  // dagger preorder is equivalence relation
  override def equivalent(a: P, b: P): Boolean = le(a,b)
}

/**
 * Laws for dagger preorder :
 * - forall p, q ∈ P we have q ≤ p whenever p ≤ q
 */
trait DaggerPreorderLaws extends PreorderLaws {
  import svarog.preorders.Preorder.ops._

  /** forall a,b ∈ X if a ≤ b then b ≤ a */
  def symmetry[X](a: X, b: X)(implicit P: Preorder[X]): Boolean =
    if(a <= b) b <= a
    else if(b <= a) a <= b
    else true
}
