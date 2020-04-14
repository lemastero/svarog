package svarog.preorders

trait SymmetricMonoidalClosedPreorder[X] extends SymmetricMonoidalPreorder[X] {
  def homElement(a: X, b: X): X
}

trait SymmetricMonoidalClosedPreorderLaws extends SymmetricMonoidalPreorderLaws {

  /** forall a,v,w ∈ X, (a⊗v) ≤ w iff a ≤ (v -* 􏰈w) */
  def lawSMCP[X](a: X, v: X, w: X)(implicit P: SymmetricMonoidalClosedPreorder[X]): Boolean = {
    import P._
    if( le(multiply(a,v), w) )
      le(a,homElement(v,w))
    else if( le(a,homElement(v,w)) )
      le(multiply(a,v), w)
    else false
  }
}
