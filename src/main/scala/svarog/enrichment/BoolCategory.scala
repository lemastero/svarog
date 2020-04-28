package svarog.enrichment

import svarog.enrichment.BoolCategory.BoolCat
import svarog.order_theory.{Preorder, SymmetricMonoidalPreorder}
import svarog.order_theory.SymmetricMonoidalPreorder.`(B, ≤, true, ∧)`
import svarog.universal_algebra.EquationalLaws

// There is a one-to-one correspondence between preorders and Bool-categories
object BoolCategory {

  type BoolCat[X] = EnrichedCategory[Boolean, X]

  def apply[X](pre: Preorder[X]): BoolCat[X] = new BoolCat[X] {
    override def base: SymmetricMonoidalPreorder[Boolean] = `(B, ≤, true, ∧)`
    override def homObject(x: X, y: X): Boolean = pre.le(x, y)
  }

  implicit def asPreorder[X](bc: BoolCat[X]): Preorder[X] =
    (a: X, b: X) => bc.homObject(a,b)
}

trait BoolCategoryLaws {
  import BoolCategory.asPreorder

  def toPreorderAndBackToBoolCategoryIsNoOp[X](bc: BoolCat[X])(a: X, b: X): Boolean = {
    EquationalLaws.isAdjoint[BoolCat[X],Preorder[X]](bc,asPreorder,BoolCategory.apply) { case (b1, b2) =>
      b1.homObject(a, b) == b2.homObject(a, b)
    }
  }

  def toBoolCatAndBackToPreorderIsNoOp[X](p: Preorder[X],a: X, b:X): Boolean = {
    EquationalLaws.isAdjoint[Preorder[X],BoolCat[X]](p,BoolCategory.apply,asPreorder) { case (b1, b2) =>
      b1.le(a, b) == b2.le(a, b)
    }
  }
}
