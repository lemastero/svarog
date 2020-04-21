package svarog.preorders

import svarog.semilattice.JoinSemilattice

trait UnitalCommutativeQuantale[X]
  extends JoinSemilattice[X]
  with SymmetricMonoidalClosedPreorder[X]
