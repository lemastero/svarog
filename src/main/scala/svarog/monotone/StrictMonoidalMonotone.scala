package svarog.monotone

import svarog.monoid.Monoid
import svarog.preorders.MonoidalPreorder

trait StrictMonoidalMonotone[P,Q] extends Function1[P,Q]

trait StrictMonoidalMonotoneLaws {
  def lawA[P,Q](p: MonoidalPreorder[P], q: MonoidalPreorder[Q], f: MonoidalMonotone[P,Q]): Boolean =
    q.unit == f(p.unit)

  def lawB[P,Q](
    p: MonoidalPreorder[P],
    q: MonoidalPreorder[Q],
    f: MonoidalMonotone[P,Q],
    p1: P, p2: P): Boolean = {
    val qm = q.multiply(f(p1), f(p2))
    val fpm = f(p.multiply(p1, p2))
    qm == fpm
  }
}

// XXX does not use Preorder structure so strong Monoid ?
trait StrictMonoidMonotone[P,Q] extends Function1[P,Q]

trait StrictMonoidMonotoneLaws {
  def lawA[P,Q](p: Monoid[P], q: Monoid[Q], f: StrictMonoidMonotone[P,Q]): Boolean =
    q.unit == f(p.unit)

  def lawB[P,Q](
      p: MonoidalPreorder[P],
      q: MonoidalPreorder[Q],
      f: MonoidalMonotone[P,Q],
      p1: P, p2: P): Boolean = {
    val qm = q.multiply(f(p1), f(p2))
    val fpm = f(p.multiply(p1, p2))
    qm == fpm
  }
}
