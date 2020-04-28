package svarog.order_theory

import svarog.semilattice.JoinSemilattice

trait UnitalCommutativeQuantale[X]
  extends JoinSemilattice[X]
  with SymmetricMonoidalClosedPreorder[X]
