package svarog.monoid

trait Monoid[X] {
  def unit: X
  def multiply(a: X, b: X): X
}

trait MonoidLaws {

  /** forall a ∈ X, I ⊗ a􏰆 = a and a ⊗ I = 􏰆a */
  def unitality[X](a: X)(implicit P: Monoid[X]): Boolean = {
    import P._
    val ua = multiply(unit, a)
    val au = multiply(a, unit)
    (ua == a) && (a == au)
  }

  /** forall a,b,c ∈ X, (a ⊗ b) ⊗ c􏰆 = a ⊗ (b ⊗ c) */
  def associativity[X](a: X, b: X, c: X)(implicit P: Monoid[X]): Boolean = {
    import P._
    val ab = multiply(a, b)
    val bc = multiply(b, c)
    multiply(ab,c) == multiply(a, bc)
  }
}


