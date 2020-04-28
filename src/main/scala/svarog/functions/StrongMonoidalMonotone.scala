package svarog.functions

import svarog.equivalence.Equivalence.ops._
import svarog.equivalence.Equivalence
import svarog.monotone.MonoidalMonotoneNaive
import svarog.order_theory.MonoidalPreorder

trait StrongMonoidalMonotone[P,Q]
  extends Function1[P,Q]

trait StrongMonoidalMonotoneLaws {
  def lawA[P,Q](
      p: MonoidalPreorder[P],
      q: MonoidalPreorder[Q],
      f: StrongMonoidalMonotone[P,Q])(implicit equiv: Equivalence[Q]): Boolean =
    q.I ~ f(p.I)

  def lawB[P,Q](
                 p: MonoidalPreorder[P],
                 q: MonoidalPreorder[Q],
                 f: MonoidalMonotoneNaive[P,Q],
                 p1: P, p2: P)(implicit eqiv: Equivalence[Q]): Boolean = {
    val qm = q.multiply(f(p1), f(p2))
    val fpm = f(p.multiply(p1, p2))
    qm ~ fpm
  }
}
