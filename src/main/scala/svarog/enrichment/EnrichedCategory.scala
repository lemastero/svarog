package svarog.enrichment

import svarog.preorders.{Preorder, SymmetricMonoidalPreorder}

trait EnrichedCategory[V, X] {
  def base: SymmetricMonoidalPreorder[V]
  def isObject(x: X): Boolean
  def homObject(x: X, y: X): V
}

trait EnrichedCategoryLaws {
  import svarog.preorders.Preorder.ops._
  import svarog.monoid.Monoid.ops._

  /** forall x ∈ Ob(X), I ≤ X(x,x) */
  def ecLaw1[V,X](x: X)(implicit ec: EnrichedCategory[V,X]): Boolean = {
    implicit val b = ec.base
    import ec._
    if(isObject(x)) base.I <= homObject(x,x)
    else true
  }

  /** forall x,y,z ∈ Ob(X), X(x,y) ⊗ X(y,z) ≤ X(x,z) */
  def ecLaw2[V,X](ec: EnrichedCategory[V,X], x: X, y: X, z: X): Boolean = {
    implicit val b = ec.base
    import ec._
    if( isObject(x) && isObject(y) && isObject(z) ) {
      val xy = homObject(x,y)
      val yz = homObject(y,z)
      val xz = homObject(x,z)
      (xy * yz) <= xz
    } else true
  }
}

object EnrichedCategory {
  type BoolCategory[X] = EnrichedCategory[Boolean, X]

  // There is a one-to-one correspondence between preorders and Bool-categories
  def preorderFrom[X](bc: BoolCategory[X]): Preorder[X] = new Preorder[X] {
    override def le(a: X, b: X): Boolean = bc.homObject(a,b)
  }

  def boolCategoryFrom[X](pre: Preorder[X]): BoolCategory[X] = new BoolCategory[X] {
    override def base: SymmetricMonoidalPreorder[Boolean] = new SymmetricMonoidalPreorder[Boolean] {
      override def I: Boolean = true
      override def multiply(a: Boolean, b: Boolean): Boolean = a || b
      override def le(a: Boolean, b: Boolean): Boolean = a <= b
    }
    override def isObject(x: X): Boolean = true
    override def homObject(x: X, y: X): Boolean = pre.le(x,y)
  }
}
