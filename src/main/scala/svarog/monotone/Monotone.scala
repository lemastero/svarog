package svarog.monotone

import svarog.preorders.Preorder

trait Monotone[P,Q] extends Function1[P,Q]

trait MonotoneLaws {

  // forall a,b ∈ X if a ≤ b then f(a) ≤ f(b)
  def monotoneLaw[P,Q](p: Preorder[P], q: Preorder[Q], f: Monotone[P,Q], a: P, b: P): Boolean =
    if( p.le(a,b) ) q.le(f(a),f(b))
    else false
}
