package svarog.functions

import svarog.EquationalLaws
import svarog.algebra.Magma

trait AdditionPreserving[X]
  extends Function1[X,X]

trait AdditionPreservingLaws {

  def preserveAddition[X](f: AdditionPreserving[X], x: X, y: X)(implicit N: Magma[X]): Boolean =
    EquationalLaws.preserveOp(x,y,f,N.multiply)
}

object AdditionPreservingLaws
  extends AdditionPreservingLaws
