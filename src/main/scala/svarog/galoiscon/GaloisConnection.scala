package svarog.galoiscon

import svarog.monotone.Monotone
import svarog.preorders.Preorder

// TODO how we could use GaloisConnection ?
// TODO how to define left adjoint ?
// TODO how to define right adjoint ?
case class GaloisConnection[P,Q](p: Preorder[P], q: Preorder[Q], f: Monotone[P,Q], g: Monotone[Q,P])

trait GaloisConnectionLaw {

  /** forall p ∈ P, q ∈ Q, f(p) ≤ q iff p ≤ g(q) */
  def transitivity[P,Q](p: P, q: Q, gc: GaloisConnection[P,Q]): Boolean =
    if( gc.q.le(gc.f(p), q) ) gc.p.le(p, gc.g(q))
    else if( gc.p.le(p, gc.g(q)) ) gc.q.le(gc.f(p), q)
    else false
}
