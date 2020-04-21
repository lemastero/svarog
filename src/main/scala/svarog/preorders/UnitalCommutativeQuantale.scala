package svarog.preorders

import svarog.meetjoin.JoinSemilattice

trait UnitalCommutativeQuantale[X]
  extends JoinSemilattice[X]
  with SymmetricMonoidalClosedPreorder[X]
