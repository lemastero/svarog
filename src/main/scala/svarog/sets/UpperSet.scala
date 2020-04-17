package svarog.sets

import svarog.preorders.Preorder
import svarog.preorders.Preorder.ops._

abstract class UpperSet[X] extends MathSet[X] {
  def pre: Preorder[X]
  def apply(p: X): Boolean
}

trait UpperSetLaws {

  /** if p ∈ U and p ≤ q, then q ∈ U */
  def upperSetLaw[X: Preorder](isUpperSet: UpperSet[X], p: X, q: X): Boolean = {
    implicit val _ = isUpperSet.pre
    if( isUpperSet(p) && (p <= q) ) isUpperSet(q)
    else true
  }
}
