package svarog.enrichment

import svarog.order_theory.{MonoidalPreorder, Preorder}
import svarog.set_theory.MathSet
import svarog.universal_algebra.EquationalLaws

trait EnrichedCategory[V, X] { self =>
  def base: MonoidalPreorder[V]
  def objects: MathSet[X] = Function.const(true)
  def homObject(x: X, y: X): V

  def Stanislaw(a: X, b: X): Boolean =
    base.le(base.I, homObject(a,b))
}

trait EnrichedCategoryLaws {
  import svarog.order_theory.MonoidalPreorder.ops._

  /** forall x ∈ Ob(X), I ≤ X(x,x) */
  def reflexivity[V,X](x: X)(implicit ec: EnrichedCategory[V,X]): Boolean =
    if(ec.objects(x)) EquationalLaws.reflexivity(x,ec.Stanislaw)
    else true

  /** forall x,y,z ∈ Ob(X), X(x,y) ⊗ X(y,z) ≤ X(x,z) */
  def triangleInequality[V,X](ec: EnrichedCategory[V,X], x: X, y: X, z: X): Boolean = {
    // TODO looks like triangleInequality but the equality is wrong :(
    implicit val b = ec.base
    import ec._
    if( objects(x) && objects(y) && objects(z) ) {
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
    override def base: MonoidalPreorder[Boolean] = new MonoidalPreorder[Boolean] {
      override def I: Boolean = true
      override def multiply(a: Boolean, b: Boolean): Boolean = a || b
      override def le(a: Boolean, b: Boolean): Boolean = a <= b
    }
    override def objects: MathSet[X] = Function.const(true)
    override def homObject(x: X, y: X): Boolean = pre.le(x,y)
  }
}
