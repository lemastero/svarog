package svarog

/**
 * Equivalence relation ~ is a binary operation that is:
 * - (reflexivity) forall a∈A, a ~ a
 * - (symmetry) forall a,b∈A, a ~ b <=> b ~ a
 * - (transitivity) forall a,b,c∈A if a ~ b and b ~ c then a ~ c
 */
trait Equivalence[A] {
  // A ~ B should be a subset of A X B
  // set (esp. infinite one) can be modeled membership:
  // member(a) = true if a is from A
  // is member (a,b) = true if a ~ b
  def eqivalent(a: A, b: A): Boolean
}

trait EquivalenceLaws {

  def reflexivity[A](a: A)(implicit E: Equivalence[A]): Boolean =
    E.eqivalent(a,a)

  def symmetry[A](a: A, b: A)(implicit E: Equivalence[A]): Boolean =
    if(E.eqivalent(a,b)) E.eqivalent(b,a)
    else if(E.eqivalent(b,a)) E.eqivalent(a,b)
    else false

  def transitivity[A](a: A, b: A, c: A)(implicit E: Equivalence[A]): Boolean =
    if (E.eqivalent(a,b) && E.eqivalent(b,c)) E.eqivalent(b,c)
    else false
}
