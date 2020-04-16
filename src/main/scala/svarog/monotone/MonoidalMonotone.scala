package svarog.monotone

import svarog.preorders.MonoidalPreorder
import svarog.monoid.Monoid.ops._
import svarog.preorders.Preorder.ops._

/** lax monoidal monotones */
trait MonoidalMonotone[P,Q] extends Function1[P,Q]

trait MonoidalMonotoneLaws {
  def lawA[P,Q](
      f: MonoidalMonotone[P,Q])(implicit
      p: MonoidalPreorder[P],
      q: MonoidalPreorder[Q]): Boolean =
    q.I <= f(p.I)

  def lawB[P,Q](
       f: MonoidalMonotone[P,Q],
       p1: P,
       p2: P)(implicit
       p: MonoidalPreorder[P],
       q: MonoidalPreorder[Q]): Boolean = {
    f(p1) * f(p2) <= f(p1 * p2)
  }
}

// TODO is it lawfull ?
// XXX to build monoidal monotone between 2 MonoidalPreorder we need function P => Q and Q => P
case class IsoMonoidalMonotone[P,Q](f: MonoidalMonotone[P,Q], g: Q => P){

  def mapMonoidalPreorderByMonoidalMonotone(implicit pre: MonoidalPreorder[P]): MonoidalPreorder[Q] =
    new MonoidalPreorder[Q] {
      def I: Q = f(pre.I)
      def multiply(a: Q, b: Q): Q = f(g(a) * g(b))
      def le(a: Q, b: Q): Boolean = g(a) <= g(b)
    }
}