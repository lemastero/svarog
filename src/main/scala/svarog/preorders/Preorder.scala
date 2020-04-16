package svarog.preorders

import simulacrum.{op, typeclass}
import svarog.Equivalence

/**
 * Preorder is a (X, ≤) set X equipped with binary relation ≤
 * that is reflexive and transitive.
 */
@typeclass
trait Preorder[X] extends Equivalence[X] { self =>
  @op("<=") def le(a: X, b: X): Boolean

  // x and y are equivalent if y ≤ x and x ≤ y
  override def equivalent(a: X, b: X): Boolean = le(a,b) && le(b,a)

  // Product preorder
  def product[Y](q: Preorder[Y]): Preorder[(X,Y)] = new Preorder[(X, Y)] {
    override def le(a: (X,Y), b: (X,Y)): Boolean = self.le(a._1, b._1) && q.le(a._2, b._2)
  }
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
    else true
}

object PreorderLaws extends PreorderLaws
