package svarog.monoid

import simulacrum.{op, typeclass}

@typeclass
trait Monoid[X] {
  def I: X

  @op("*")
  def multiply(a: X, b: X): X
}

trait MonoidLaws {

  /** forall a ∈ X, I ⊗ a􏰆 = a and a ⊗ I = 􏰆a */
  def unitality[X](a: X)(implicit P: Monoid[X]): Boolean = {
    import Monoid.ops._
    import P.I
    ((I * a) == a) && (a == (a * I))
  }

  /** forall a,b,c ∈ X, (a ⊗ b) ⊗ c􏰆 = a ⊗ (b ⊗ c) */
  def associativity[X](a: X, b: X, c: X)(implicit P: Monoid[X]): Boolean = {
    import Monoid.ops._
    ((a * b) * c) == (a * (b * c))
  }
}

object MonoidLaws extends MonoidLaws {}


