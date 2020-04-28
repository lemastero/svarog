package svarog.set_theory

import svarog.monotone.Monotone
import svarog.order_theory.Preorder

abstract class Pullback[P,Q] {
  def p: Preorder[P]
  def q: Preorder[Q]
  def f: Monotone[P,Q]
  def u: UpperSet[Q]
  // TODO image of the function ?
}
