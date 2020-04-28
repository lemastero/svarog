package svarog.order_theory

import simulacrum.{op, typeclass}
import svarog.order_theory.SymmetricMonoidalClosedPreorder.MonoidalClosed
import svarog.universal_algebra.EquationalLaws

@typeclass
trait SymmetricMonoidalClosedPreorder[X]
  extends SymmetricMonoidalPreorder[X] {

  @op("-*")
  def homElement(a: X, b: X): X
}

trait SymmetricMonoidalClosedPreorderLaws
  extends SymmetricMonoidalPreorderLaws {

  /** forall a,v,w ∈ X, (a⊗v) ≤ w iff a ≤ (v -* 􏰈w) */
  def minusPlusPreorder[X](a: X, v: X, w: X)(implicit P: MonoidalClosed[X]): Boolean =
    EquationalLaws.minusPlusPreorder(a,v,w,P.multiply,P.homElement,P.le)
}

object SymmetricMonoidalClosedPreorder {

  type MonoidalClosed[X] = SymmetricMonoidalClosedPreorder[X]

  def apply[X](ord: (X,X) => Boolean, e: X, prod: (X,X) => X, hom: (X,X) => X): MonoidalClosed[X] =
    new MonoidalClosed[X] {
      override def I: X = e
      override def le(a: X, b: X): Boolean = ord(a,b)
      override def multiply(a: X, b: X): X = prod(a,b)
      override def homElement(a: X, b: X): X = hom(a,b)
  }
}
