package svarog.monotone

import svarog.Equivalence
import svarog.preorders.MonoidalPreorder

trait StrongMonoidalMonotone[P,Q] extends Function1[P,Q]

trait StrongMonoidalMonotoneLaws {
  def lawA[P,Q](
      p: MonoidalPreorder[P],
      q: MonoidalPreorder[Q],
      f: StrongMonoidalMonotone[P,Q],
      eqiv: Equivalence[Q]): Boolean =
    eqiv.eqivalent(q.unit,f(p.unit))

  def lawB[P,Q](
      p: MonoidalPreorder[P],
      q: MonoidalPreorder[Q],
      f: MonoidalMonotone[P,Q],
      p1: P, p2: P,
      eqiv: Equivalence[Q]): Boolean = {
    val qm = q.multiply(f(p1), f(p2))
    val fpm = f(p.multiply(p1, p2))
    eqiv.eqivalent(qm, fpm)
  }
}
