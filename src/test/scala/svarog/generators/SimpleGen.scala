package svarog.generators

import java.math.BigInteger

import hedgehog.Gen.int
import hedgehog.Gen.long
import hedgehog.Gen.boolean
import hedgehog.Gen
import hedgehog.Range
import hedgehog.core.GenT

import scala.math.BigInt

object SimpleGen {
  def mkGenInt: Unit => GenT[Int] = _ => int(Range.linear(Int.MinValue, Int.MaxValue))
  def mkGenLong: Unit => GenT[Long] = _ => long(Range.linear(Long.MinValue, Long.MaxValue))
  def mkGenBigInt: Unit => GenT[BigInt] = _ => mkGenLong(()).map(long2big)
  def long2big(a: Long): BigInt = new BigInt(BigInteger.valueOf(a))
  def mkBooleanGen: Unit => Gen[Boolean] = _ => boolean
}
