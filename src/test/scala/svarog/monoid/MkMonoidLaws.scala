package svarog.monoid

import hedgehog.{Property, Result}
import hedgehog.core.GenT
import hedgehog.runner.Test

object MkMonoidLaws {

  def mkMonoidalLaws[T](mkGen: Unit => GenT[T], name: String)(implicit monoid: Monoid[T]): List[Test] = {
    List(
      Test(s"$name Monoid Laws: unitality", unitality(mkGen)),
      Test(s"$name Monoid Laws: associativity", associativity(mkGen))
    )
  }

  def unitality[T](mkGen: Unit => GenT[T])(implicit M: Monoid[T]): Property = {
    for {
      a <- mkGen(()).lift
    } yield Result.assert( MonoidLaws.unitality[T](a) )
  }

  def associativity[T](mkGen: Unit => GenT[T])(implicit M: Monoid[T]): Property = {
    for {
      a <- mkGen(()).lift
      b <- mkGen(()).lift
      c <- mkGen(()).lift
    } yield Result.assert( MonoidLaws.associativity(a,b,c) )
  }
}
