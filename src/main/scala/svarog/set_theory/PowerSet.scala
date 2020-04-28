package svarog.set_theory

class PowerSet[X](all: Set[Set[X]]) extends MathSet[Set[X]] {
  override def apply(x: Set[X]): Boolean =
    all.contains(x)
}

object PowerSet {

  // set of all susets
//  class PowersetOfType[X] {
//    def apply[Y]: Boolean =
//      classOf[Y].isAssignableFrom(classOf[X])
//  }

  class SetOfPowerSet[X](x: MathSet[X]) {
    def apply[Y <: X](y: Y): Boolean = x(y)
  }
}
