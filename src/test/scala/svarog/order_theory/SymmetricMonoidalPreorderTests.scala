package svarog.order_theory

import hedgehog.runner.{Prop, Properties}
import svarog.generators.SimpleGen.mkBooleanGen
import svarog.generators.SimpleGen.mkGenBigInt
import svarog.order_theory.MkSymmetricMonoidalPreorderLaws.mkSymmetricMonoidalPreorderLaws

class SymmetricMonoidalPreorderTests extends Properties {

  def tests: List[Prop] = List.empty ++
    mkSymmetricMonoidalPreorderLaws(mkGenBigInt, "(BigInt,<=,+,0)")(SymmetricMonoidalPreorder.SMPBigIntPlusLePlus) ++
    mkSymmetricMonoidalPreorderLaws(mkGenBigInt, "(BigInt,>=,+,1)")(SymmetricMonoidalPreorder.SMPBigIntMultiGePlus) ++
    //mkSymmetricMonoidalPreorderLaws(mkGenBigInt, "(BigInt,<=,*,1)")(SymmetricMonoidalPreorder.SMPBigIntMultiGeTimes) ++
    mkSymmetricMonoidalPreorderLaws(mkBooleanGen, "(Boolean,<=,||,false)")(SymmetricMonoidalPreorder.`(B, ≤, false, ∨)`) ++
    mkSymmetricMonoidalPreorderLaws(mkBooleanGen, "(Double,<=,&&,true)")(SymmetricMonoidalPreorder.`(B, ≤, true, ∧)`) ++
    //mkSymmetricMonoidalPreorderLaws(mkBooleanGen, "(Double,<=,&&,true)")(RealWithInfinity.smp)
    List.empty

}
