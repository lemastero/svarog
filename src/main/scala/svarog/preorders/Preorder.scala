package svarog.preorders

/**
 * Preorder is a (X, ≤) set X equipped with binary relation ≤
 * that is reflexive and transitive.
 */
trait Preorder[X] {
  def le(a: X, b: X): Boolean
}

/**
 * Laws for preorder:
 * - reflexivity: forall a ∈ X, a ≤ a
 * - transitivity: forall a,b,c ∈ X if a ≤ b and b ≤ c then a ≤ c
 */
trait PreorderLaws {
  /** forall a ∈ X, a ≤ a */
  def reflexivity[X](a: X)(implicit P: Preorder[X]): Boolean =
    P.le(a,a)

  /** forall a,b,c ∈ X if a ≤ b and b ≤ c then a ≤ c */
  def transitivity[X](a: X, b: X, c: X)(implicit P: Preorder[X]): Boolean =
    if(P.le(a,b) && P.le(b,c)) P.le(a,c)
    else false
}
