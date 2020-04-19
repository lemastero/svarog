package svarog.preorders

import simulacrum.typeclass

/**
 * Preorder with symmetric monoidal structure is a preorder (X, ≤, ⊗, I) with monoidal structure
 * where monoidal multiplication is symmetric:
 * - symmetry: forall a,b ∈ X, a ⊗ b􏰆 = b ⊗ a
 */
@typeclass
trait SymmetricMonoidalPreorder[X]
  extends MonoidalPreorder[X]

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

object SymmetricMonoidalPreorder {

  def newSMP[X](lessThan: (X,X) => Boolean, m: (X,X) => X, e: X): SymmetricMonoidalPreorder[X] =
    new SymmetricMonoidalPreorder[X] {
      override def I: X = e
      override def multiply(a: X, b: X): X = m(a,b)
      override def le(a: X, b: X): Boolean = lessThan(a,b)
    }

  val SMPBigIntPlusLePlus: SymmetricMonoidalPreorder[BigInt] =
    newSMP[BigInt](_ <= _, _ + _, BigInt(0))
  val SMPBigIntMultiGeTimes: SymmetricMonoidalPreorder[BigInt] =
    newSMP[BigInt](_ >= _, _ * _, BigInt(1))
  val SMPBigIntMultiGePlus: SymmetricMonoidalPreorder[BigInt] =
    newSMP[BigInt](_ >= _, _ + _, BigInt(0))

  val SMPBoolAndLe: SymmetricMonoidalPreorder[Boolean] =
    newSMP[Boolean](_ <= _, _ && _, true)
  val SMPBoolOrLe: SymmetricMonoidalPreorder[Boolean] =
    newSMP[Boolean](_ <= _, _ || _, false)
}
