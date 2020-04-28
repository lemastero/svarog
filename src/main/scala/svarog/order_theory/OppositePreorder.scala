package svarog.order_theory

case class OppositePreorder[X](p: Preorder[X]) extends Preorder[X] {
  def le(a: X, b: X): Boolean = p.le(b,a)
}
