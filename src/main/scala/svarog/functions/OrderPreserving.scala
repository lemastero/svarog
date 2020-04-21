package svarog.functions

import svarog.preorders.Preorder
import svarog.preorders.Preorder.ops._

trait OrderPreserving[X]
  extends Function1[X,X]

trait OrderPreservingLaws {

  def preserveOrder[X](f: OrderPreserving[X], x: X, y: X)(implicit N: Preorder[X]): Boolean =
    if( x <= y) f(x) <= f(y)
    else true
}

object OrderPreservingLaws
  extends OrderPreservingLaws
