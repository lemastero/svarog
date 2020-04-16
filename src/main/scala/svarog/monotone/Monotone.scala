package svarog.monotone

import svarog.preorders.Preorder

trait Monotone[P,Q] extends Function1[P,Q]

trait MonotoneLaws {
  import svarog.preorders.Preorder.ops._

  /** forall a,b ∈ X if a ≤ b then f(a) ≤ f(b) */
  def monotoneLaw[P,Q](
      f: Monotone[P,Q], a: P, b: P)(implicit
      p: Preorder[P],
      q: Preorder[Q]): Boolean =
    if( a <= b ) f(a) <= f(b)
    else true
}
