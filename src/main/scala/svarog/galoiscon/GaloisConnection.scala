package svarog.galoiscon

import svarog.monotone.Monotone
import svarog.order_theory.Preorder

// TODO how to define left adjoint ?
// TODO how to define right adjoint ?
case class GaloisConnection[P,Q](
  p: Preorder[P],
  q: Preorder[Q],
  f: Monotone[P,Q],
  g: Monotone[Q,P]
)

trait GaloisConnectionLaw {
  import svarog.order_theory.Preorder.ops._

  /** forall p ∈ P, q ∈ Q, f(p) ≤ q iff p ≤ g(q) */
  def transitivity[P,Q](p: P, q: Q, gc: GaloisConnection[P,Q]): Boolean = {
    implicit val pp = gc.p
    implicit val qq = gc.q
    if( gc.f(p) <= q ) p <= gc.g(q)
    else if( p <= gc.g(q) ) gc.f(p) <= q
    else true
  }
}
