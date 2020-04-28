package svarog.order_theory

import hedgehog.{Property, Result}
import hedgehog.core.GenT
import hedgehog.runner.Test

object MkPreorderLaws {

  def mkPreorderLaws[T](mkGen: Unit => GenT[T], name: String)(implicit monoid: Preorder[T]): List[Test] = {
    List(
      Test(s"$name Preorder Laws: reflexivity", reflexivity(mkGen)),
      Test(s"$name Preorder Laws: transitivity", transitivity(mkGen))
    )
  }

  def reflexivity[T](mkGen: Unit => GenT[T])(implicit M: Preorder[T]): Property = {
    for {
      a <- mkGen(()).lift
    } yield Result.assert( PreorderLaws.reflexivity[T](a) )
  }

  def transitivity[T](mkGen: Unit => GenT[T])(implicit M: Preorder[T]): Property = {
    for {
      a <- mkGen(()).lift
      b <- mkGen(()).lift
      c <- mkGen(()).lift
    } yield Result.assert( PreorderLaws.transitivity(a,b,c) )
  }
}
