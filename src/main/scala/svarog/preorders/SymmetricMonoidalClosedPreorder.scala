package svarog.preorders

trait SymmetricMonoidalClosedPreorder[X] extends SymmetricMonoidalPreorder[X] {
  def homElement(a: X, b: X): X
}

trait SymmetricMonoidalClosedPreorderLaws extends SymmetricMonoidalPreorderLaws {
  import svarog.preorders.Preorder.ops._
  import svarog.monoid.Monoid.ops._

  /** forall a,v,w ∈ X, (a⊗v) ≤ w iff a ≤ (v -* 􏰈w) */
  def lawSMCP[X](a: X, v: X, w: X)(implicit P: SymmetricMonoidalClosedPreorder[X]): Boolean = {
    import P.homElement
    if( (a * v) <= w ) a <= homElement(v,w)
    else if( a <= homElement(v,w) ) (a * v) <= w
    else true
  }
}
