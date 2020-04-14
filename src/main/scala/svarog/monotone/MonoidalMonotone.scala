package svarog.monotone

import svarog.preorders.MonoidalPreorder

trait MonoidalMonotone[P,Q] extends Function1[P,Q]

trait MonoidalMonotoneLaws {
  def lawA[P,Q](p: MonoidalPreorder[P], q: MonoidalPreorder[Q], f: MonoidalMonotone[P,Q]): Boolean =
    q.le(q.unit, f(p.unit))

  def lawB[P,Q](p: MonoidalPreorder[P], q: MonoidalPreorder[Q], f: MonoidalMonotone[P,Q], p1: P, p2: P): Boolean = {
    val qm = q.multiply(f(p1), f(p2))
    val fpm = f(p.multiply(p1, p2))
    q.le(qm, fpm)
  }
}

// TODO does it lawfull ?
// XXX to build monoidal monotone between 2 MonoidalPreorder we need function P => Q and Q => P
case class IsoMonoidalMonotone[P,Q](mm: MonoidalMonotone[P,Q], g: Q => P){

  def mapMonoidalPreorderByMonoidalMonotone(pre: MonoidalPreorder[P]): MonoidalPreorder[Q] =
    new MonoidalPreorder[Q] {
      def unit: Q = mm(pre.unit)
      def multiply(a: Q, b: Q): Q = mm(pre.multiply(g(a), g(b)))
      def le(a: Q, b: Q): Boolean = pre.le(g(a),g(b))
    }
}