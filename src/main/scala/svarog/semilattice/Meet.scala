package svarog.semilattice

import svarog.order_theory.Preorder

trait Meet[A,P] {
  def pre: Preorder[P]
}
