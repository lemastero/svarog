package svarog.preorders

import hedgehog.runner.{Prop, Properties}
import svarog.generators.SimpleGen.mkBooleanGen
import svarog.generators.SimpleGen.mkGenBigInt
import svarog.preorders.MkSymmetricMonoidalPreorderLaws.mkSymmetricMonoidalPreorderLaws
import svarog.preorders.SymmetricMonoidalPreorder._

class SymmetricMonoidalPreorderTests extends Properties {

  def tests: List[Prop] = List.empty ++
    mkSymmetricMonoidalPreorderLaws(mkGenBigInt, "(BigInt,<=,+,0)")(SMPBigIntPlusLe) ++
    mkSymmetricMonoidalPreorderLaws(mkGenBigInt, "(BigInt,<=,*,1)")(SMPBigIntMultiGe) ++
    mkSymmetricMonoidalPreorderLaws(mkBooleanGen, "(Boolean,<=,||,false)")(SMPBoolOrLe) ++
    mkSymmetricMonoidalPreorderLaws(mkBooleanGen, "(Double,<=,&&,true)")(SMPBoolAndLe)

}
