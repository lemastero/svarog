package svarog.functions

import svarog.algebra.Monoid
import svarog.algebra.Monoid.ops._
import svarog.preorders.MonoidalPreorder

trait StrictMonoidalMonotone[P,Q]
  extends Function1[P,Q]

trait StrictMonoidalMonotoneLaws {
  def lawA[P,Q](
      p: MonoidalPreorder[P],
      q: MonoidalPreorder[Q],
      f: MonoidalMonotoneNaive[P,Q]): Boolean =
    q.I == f(p.I)

  def lawB[P,Q](
                 f: MonoidalMonotoneNaive[P,Q],
                 p1: P, p2: P)(implicit
       p: MonoidalPreorder[P],
       q: MonoidalPreorder[Q]): Boolean = {
    val qm = f(p1) * f(p2)
    val fpm = f(p1 * p2)
    qm == fpm
  }
}

// XXX does not use Preorder structure so strong Monoid ?
trait StrictMonoidMonotone[P,Q] extends Function1[P,Q]

trait StrictMonoidMonotoneLaws {
  def lawA[P,Q](p: Monoid[P], q: Monoid[Q], f: StrictMonoidMonotone[P,Q]): Boolean =
    q.I == f(p.I)

  def lawB[P,Q](
                 f: MonoidalMonotoneNaive[P,Q],
                 p1: P, p2: P)(implicit
      p: MonoidalPreorder[P],
      q: MonoidalPreorder[Q]): Boolean = {
    val qm = f(p1) * f(p2)
    val fpm = f(p1 * p2)
    qm == fpm
  }
}
