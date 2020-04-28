package svarog.order_theory

case class ProductPreorder[X,Y](
 p: Preorder[X],
 q: Preorder[Y]
) extends Preorder[(X, Y)] {

  override def le(a: (X,Y), b: (X,Y)): Boolean =
    p.le(a._1, b._1) && q.le(a._2, b._2)
}
