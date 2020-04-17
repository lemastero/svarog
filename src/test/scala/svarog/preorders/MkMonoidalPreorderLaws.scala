package svarog.preorders

import hedgehog._
import hedgehog.core.GenT
import hedgehog.runner.Test
import svarog.monoid.MkMonoidLaws.mkMonoidalLaws
import svarog.preorders.MkPreorderLaws.mkPreorderLaws

object MkMonoidalPreorderLaws {

  def mkMonoidalPreorderLaws[T](mkGen: Unit => GenT[T], name: String)(implicit monoid: MonoidalPreorder[T]): List[Test] = {
    List() ++
      mkPreorderLaws(mkGen, name) ++
      mkMonoidalLaws(mkGen, name) ++
      List( Test(s"$name Monoidal Preorder: monotonicity", monotonicity(mkGen)) )
  }

  def monotonicity[T](mkGen: Unit => GenT[T])(implicit M: MonoidalPreorder[T]): Property = {
    for {
      a <- mkGen(()).lift
      b <- mkGen(()).lift
      c <- mkGen(()).lift
      d <- mkGen(()).lift
    } yield Result.assert(MonoidalPreorderLaws.monotonicity(a,b,c,d))
      .log(s"if( ($a <= $c) && ($b <= $d) ) ($a * $b) <= ($c * $d)")
  }
}
