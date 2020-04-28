package svarog.functions

import svarog.monotone.MonoidalMonotoneNaive
import svarog.order_theory.MonoidalPreorder

trait OplaxMonoidalMonotone[P,Q] extends Function1[P,Q]

trait OplaxMonoidalMonotoneLaws {
  import svarog.algebra.Monoid.ops._
  import svarog.order_theory.Preorder.ops._

  def lawA[P,Q](implicit p: MonoidalPreorder[P], q: MonoidalPreorder[Q], f: MonoidalMonotoneNaive[P,Q]): Boolean =
    f(p.I) <= q.I

  def lawB[P,Q](
        p1: P,
        p2: P)(implicit
        p: MonoidalPreorder[P],
        q: MonoidalPreorder[Q],
        f: MonoidalMonotoneNaive[P,Q]): Boolean = {
    f(p1 * p2) <= (f(p1) * f(p2))
  }
}
