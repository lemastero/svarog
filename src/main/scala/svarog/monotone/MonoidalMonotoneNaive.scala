package svarog.monotone

import svarog.order_theory.MonoidalPreorder
import svarog.order_theory.MonoidalPreorder.ops._

/** lax monoidal monotones */
trait MonoidalMonotoneNaive[P,Q]
  extends Function1[P,Q]

trait MonoidalMonotoneNaiveLaws {
  def lawA[P,Q](
    f: MonoidalMonotoneNaive[P,Q])(implicit
    p: MonoidalPreorder[P],
    q: MonoidalPreorder[Q]): Boolean =
  q.I <= f(p.I)

  def lawB[P,Q](
      f: MonoidalMonotoneNaive[P,Q],
      p1: P,
      p2: P)(implicit
      p: MonoidalPreorder[P],
      q: MonoidalPreorder[Q]): Boolean =
   f(p1) * f(p2) <= f(p1 * p2)
}
