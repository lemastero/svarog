package svarog.functions

import svarog.equivalence.Equivalence.ops._
import svarog.equivalence.Equivalence
import svarog.monotone.{MonoidalMonotone, MonoidalMonotoneLaws, MonoidalMonotoneNaive}
import svarog.order_theory.MonoidalPreorder

/**
 * Let (P, <=p, Ip, *p) and (Q, <=q, Iq, *q) be a monoidal preorders.
 * A monoidal monotone from (P, <=p, Ip, *p) to (Q, <=q, Iq, *q) is a monotone map:
 * f: (P, <=p) -> (Q, <=q) satisfying:
 * a) Iq <=q f(Ip)
 * b) f(p1) *q f(p2) <=q f(p *p p2)
 *
 * Monoidal monotone is strong if it satisfy 2 additional conditions:
 * c) Iq ~ f(Ip)
 * d) f(p1) *q f(p2) ~ f(p1 *p p2)
 */
trait StrongMonoidalMonotone[P,Q]
  extends MonoidalMonotone[P,Q]

trait StrongMonoidalMonotoneLaws extends MonoidalMonotoneLaws {
  def lawC[P,Q](
      p: MonoidalPreorder[P],
      q: MonoidalPreorder[Q],
      f: StrongMonoidalMonotone[P,Q])(implicit equiv: Equivalence[Q]): Boolean =
    q.I ~ f(p.I) // TODO equivalence should be the one induced by preorder (a ~ b) <=> ( (p <= q) && (q <= p) )

  def lawD[P,Q](
      p: MonoidalPreorder[P],
      q: MonoidalPreorder[Q],
      f: MonoidalMonotoneNaive[P,Q],
      p1: P, p2: P)(implicit eqiv: Equivalence[Q]): Boolean = {
    val qm = q.multiply(f(p1), f(p2))
    val fpm = f(p.multiply(p1, p2))
    qm ~ fpm
  }
}
