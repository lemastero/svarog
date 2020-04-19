package svarog.enrichment

import svarog.enrichment.BoolCategory.BoolCategory
import svarog.preorders.{Preorder, SymmetricMonoidalPreorder}
import svarog.preorders.SymmetricMonoidalPreorder.SMPBoolAndLe

// There is a one-to-one correspondence between preorders and Bool-categories
object BoolCategory {

  type BoolCategory[X] = EnrichedCategory[Boolean, X]

  def apply[X](pre: Preorder[X]): BoolCategory[X] = new BoolCategory[X] {
    override def base: SymmetricMonoidalPreorder[Boolean] = SMPBoolAndLe
    override def homObject(x: X, y: X): Boolean = pre.le(x, y)
  }

  implicit def asPreorder[X](implicit bc: BoolCategory[X]): Preorder[X] =
    (a: X, b: X) => bc.homObject(a,b)
}

trait BoolCategoryLaws {
  import BoolCategory.asPreorder

  def property1[X](bc: BoolCategory[X]): Boolean = {
    val p = asPreorder(bc)
    val bc2 = BoolCategory(p)
    bc == bc2 // TODO we need equivalence for BoolCategory[X]
  }

  def property2[X](p: Preorder[X]): Boolean = {
    val bc = BoolCategory(p)
    val p2 = asPreorder(bc)
    p2 == p // TODO we need equivalence for BoolCategory[X]
  }
}
