package svarog.preorders

import hedgehog.{Property, Result}
import hedgehog.core.GenT
import hedgehog.runner.Test
import svarog.preorders.MkMonoidalPreorderLaws.mkMonoidalPreorderLaws

object MkSymmetricMonoidalPreorderLaws {

  def mkSymmetricMonoidalPreorderLaws[T](mkGen: Unit => GenT[T], name: String)(implicit monoid: SymmetricMonoidalPreorder[T]): List[Test] = {
    List() ++
      mkMonoidalPreorderLaws(mkGen, name) ++
      List( Test(s"$name Symmetric monoidal preorder: symmetry", symmetry(mkGen)) )
  }

  def symmetry[T](mkGen: Unit => GenT[T])(implicit M: SymmetricMonoidalPreorder[T]): Property = {
    for {
      a <- mkGen(()).lift
      b <- mkGen(()).lift
    } yield Result.assert( SymmetricMonoidalPreorderLaws.symmetry(a,b) )
  }
}
