package svarog.algebra

trait CoSymmetricCoMonoid[X] extends CoMonoid[X]

trait CoSymmetricCoMonoidLaws extends ComonoidLaws {

  def cosymmetry[X](a: X)(implicit P: CoSemigroup[X]): Boolean = {
    val lhs = P.duplicate(a)
    val rhs = P.duplicate(a) match { case(a1,a2) => (a2,a1) }
    lhs == rhs
  }
}

object CoSymmetricCoMonoidLaws extends CoSymmetricCoMonoidLaws {}


