package svarog.functions

import svarog.monotone.MonoidalMonotoneNaive
import svarog.order_theory.MonoidalPreorder

/**
 * Let (P, <=p, Ip, *p) and (Q, <=q, Iq, *q) be a monoidal preorders.
 * A (lax) monoidal monotone from (P, <=p, Ip, *p) to (Q, <=q, Iq, *q) is a monotone map:
 * f: (P, <=p) -> (Q, <=q) satisfying:
 * a) Iq <=q f(Ip)
 * b) f(p1) *q f(p2) <=q f(p *p p2)
 */
trait OplaxMonoidalMonotone[P,Q] extends Function1[P,Q] {
  def map(x: MonoidalPreorder[P]): MonoidalPreorder[Q]
}

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
