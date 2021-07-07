package svarog.functions

import svarog.algebra.Monoid
import svarog.algebra.Monoid.ops._
import svarog.monotone.{MonoidalMonotone, MonoidalMonotoneLaws, MonoidalMonotoneNaive}
import svarog.order_theory.MonoidalPreorder

/**
 * Let (P, <=p, Ip, *p) and (Q, <=q, Iq, *q) be a monoidal preorders.
 * A monoidal monotone from (P, <=p, Ip, *p) to (Q, <=q, Iq, *q) is a monotone map:
 * f: (P, <=p) -> (Q, <=q) satisfying:
 * a) Iq <=q f(Ip)
 * b) f(p1) *q f(p2) <=q f(p *p p2)
 *
 * Monoidal monotone is strict if it satisfy 2 additional conditions:
 * c) Iq = f(Ip)
 * d) f(p1) *q f(p2) = f(p1 *p p2)
 */
trait StrictMonoidalMonotone[P,Q]
  extends MonoidalMonotone[P,Q]

trait StrictMonoidalMonotoneLaws extends MonoidalMonotoneLaws {
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
