package svarog.preorders

/**
 * Preorder with symmetric monoidal structure is a preorder (X, ≤, ⊗, I) with monoidal structure
 * where monoidal multiplication is symmetric:
 * - symmetry: forall a,b ∈ X, a ⊗ b􏰆 = b ⊗ a
 */
trait SymmetricMonoidalPreorder[X] extends MonoidalPreorder[X]

/**
 * Laws for SymmetricMonoidalPreorder:
 * - symmetry: forall a,b ∈ X, a ⊗ b􏰆 = b ⊗ a
 */
trait SymmetricMonoidalPreorderLaws extends MonoidalPreorderLaws {

  /** forall a,b ∈ X, a ⊗ b􏰆 = b ⊗ a */
  def symmetry[X](a: X, b: X)(implicit P: SymmetricMonoidalPreorder[X]): Boolean = {
    import P._
    multiply(a,b) == multiply(b,a)
  }
}

object SymmetricMonoidalPreorderLaws extends SymmetricMonoidalPreorderLaws

// TODO check
object SMPIntPlusLe extends SymmetricMonoidalPreorder[Int] {
  override val I: Int = 0
  override def multiply(a: Int, b: Int): Int = a + b // TODO overflow will break laws?
  override def le(a: Int, b: Int): Boolean = a <= b
}

object SMPIntMultiLe extends SymmetricMonoidalPreorder[Int] {
  override val I: Int = 1
  override def multiply(a: Int, b: Int): Int = a * b // TODO overflow will break laws?
  override def le(a: Int, b: Int): Boolean = a <= b
}

object SMPBoolAndLe extends SymmetricMonoidalPreorder[Boolean] {
  override val I: Boolean = true
  override def multiply(a: Boolean, b: Boolean): Boolean = a && b
  override def le(a: Boolean, b: Boolean): Boolean = a <= b
}

object SMPBoolOrLe extends SymmetricMonoidalPreorder[Boolean] {
  override val I: Boolean = false
  override def multiply(a: Boolean, b: Boolean): Boolean = a || b
  override def le(a: Boolean, b: Boolean): Boolean = a <= b
}

object SMPIntOrLe extends SymmetricMonoidalPreorder[Int] {
  override val I: Int = Int.MaxValue
  override def multiply(a: Int, b: Int): Int = Math.min(a,b)
  override def le(a: Int, b: Int): Boolean = a <= b
}

// TODO
