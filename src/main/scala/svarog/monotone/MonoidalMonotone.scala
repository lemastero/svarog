package svarog.monotone

import svarog.order_theory.MonoidalPreorder
import svarog.order_theory.MonoidalPreorder.ops._
import svarog.universal_algebra.EquationalLaws

/**
 * Let (P,<=p,Ip,*p) and (Q,<=q,Iq,*q) be a monoidal preorders.
 * A monidal monotone from (P,<=p,Ip,*p) to (Q,<=q,Iq,*q) is a monotone map:
 * f: (P,)
 * @tparam P
 * @tparam Q
 */
trait MonoidalMonotone[P,Q]
  extends Function1[P,Q] {

  def map(x: MonoidalPreorder[P]): MonoidalPreorder[Q]
}

trait MonoidalMonotoneLaws {
  def lawA[P, Q](f: MonoidalMonotone[P,Q], p: MonoidalPreorder[P]): Boolean = {
    implicit val q: MonoidalPreorder[Q] = f.map(p)
    q.I <= f(p.I)
   }

  def lawB[P,Q](f: MonoidalMonotone[P,Q], p1: P, p2: P)(implicit p: MonoidalPreorder[P]): Boolean = {
    val q: MonoidalPreorder[Q] = f.map(p)
    EquationalLaws.preserveOp(p1,p2,f,p.multiply,q.multiply,q.le)
  }
}


