package svarog.monotone

import svarog.preorders.MonoidalPreorder
import svarog.preorders.MonoidalPreorder.ops._

trait MonoidalMonotone[P,Q]
  extends Function1[P,Q] {

  def map(x: MonoidalPreorder[P]): MonoidalPreorder[Q]
}

trait MonoidalMonotoneLaws {
  def lawA[P, Q](
      f: MonoidalMonotone[P,Q],
      p: MonoidalPreorder[P]): Boolean = {
    implicit val q: MonoidalPreorder[Q] = f.map(p)
    q.I <= f(p.I)
   }

  def lawB[P,Q](
      f: MonoidalMonotone[P,Q],
      p1: P,
      p2: P)(implicit p: MonoidalPreorder[P]): Boolean = {
    implicit val q: MonoidalPreorder[Q] = f.map(p)
    f(p1) * f(p2) <= f(p1 * p2)
  }
}


