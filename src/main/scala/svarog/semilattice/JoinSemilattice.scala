package svarog.semilattice

import svarog.preorders.{Preorder, PreorderLaws}

// TODO this definition is special form where we take single element subsets
// assume all joins exists
// For any two subsets A and B, join A \/ B
// is smallest subset that is bigger than both A and B
// A <= (A \/ B)
// B <= (A \/ B)
// forall C if A <= C and B <= C then (A \/ B) <= C

// if all joins exists the we have semilattice
trait JoinSemilattice[X]
  extends Preorder[X] { // TODO use typeclass instead of inherintence ?

  def join(a: X, b: X): X
  // TODO what would be 0 = join of empty set
}

trait JoinSemilatticeLaws extends PreorderLaws {
  import Preorder.ops._

  /** forall a,b ∈ X, a ≤ ( a \/ b ) */
  def jsLaw1[X](a: X, b: X)(implicit P: JoinSemilattice[X]): Boolean =
    a <= P.join(a,b)

  /** forall a,b ∈ X, b ≤ ( a \/ b ) */
  def jsLaw2[X](a: X, b: X)(implicit P: JoinSemilattice[X]): Boolean =
    b <= P.join(a,b)

  /** forall a,b,c ∈ X, if a <= c and b <= c then ( a \/ b ) <= c */
  def jsLaw3[X](a: X, b: X, c: X)(implicit P: JoinSemilattice[X]): Boolean =
    if(a <= c && b <= c) P.join(a,b) <= c
    else true
}
