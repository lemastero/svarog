package svarog.functions

import svarog.algebra.Magma
import svarog.universal_algebra.EquationalLaws

trait AdditionPreserving[X]
  extends Function1[X,X]

trait AdditionPreservingLaws {

  def preserveAddition[X](f: AdditionPreserving[X], x: X, y: X)(implicit N: Magma[X]): Boolean =
    EquationalLaws.preserveOp(x,y,f,N.multiply)
}

object AdditionPreservingLaws
  extends AdditionPreservingLaws
