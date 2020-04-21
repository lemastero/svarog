package svarog

object EquationalLaws {

  // I * a == a and a == a * I
  def unitality[X](a: X, binOp: (X,X) => X, unit: X): Boolean =
    (binOp(unit, a) == a) && (a == binOp(a, unit))

  // (a * b) * c == a * (b * c)
  def associativity[X](a: X, b: X, c: X, binOp: (X,X) => X): Boolean =
    binOp(binOp(a,b), c) == binOp(a, binOp(b,c))

  // a - a == 0
  def reflexivity[X](a: X, rel: (X,X) => Boolean): Boolean =
    rel(a,a)

  // a < b and b < c then a < c
  def transitivity[X](a: X, b: X, c: X, rel: (X,X) => Boolean): Boolean =
    if(rel(a,b) && rel(b,c)) rel(a,c)
    else true

  // a * b == b * a
  // if( a ~ b ) => b ~ a
  def symmetry[X,Y](a: X, b: X, binOp: (X,X) => Y): Boolean =
    binOp(a,b) == binOp(b,a)

  def monotonicity[X](a1: X, a2: X, b1: X, b2: X, binOp: (X,X) => X, rel: (X,X) => Boolean): Boolean =
    if( rel(a1, b1) && rel(a2, b2) ) rel( binOp(a1, a2), binOp(b1, b2) )
    else true

  // if a ~ b then a == b
  def skeletality[X](a: X, b: X, rel: (X,X) => Boolean): Boolean =
    if( rel(a,b) ) a == b
    else true

  // if (a + b) <= c then a <= b - c
  // - could be monus(a,b) = max(0,b-a)
  // TODO what is the mathematical name of this property
  def minusPlusPreorder[X](a: X, b: X, c: X, plus: (X,X) => X, monus: (X, X) => X, lessOrEqual: (X,X) => Boolean): Boolean = {
    if( lessOrEqual(plus(a,b), c) ) lessOrEqual(a, monus(b,c) )
    else if( lessOrEqual(a, monus(b,c) ) ) lessOrEqual( plus(a,b), c)
    else true
  }

  // TODO this is probabl wrong - generalized badly - redo it
  def triangleInequality[X,Y](x: X, y: X, z: X, distance: (X,X) => Y, lessOrEqual: (Y,Y) => Boolean, plus: (Y,Y) => Y): Boolean =
    lessOrEqual(
      distance(x,z),
      plus(distance(x,y), distance(y,z))
    )

  // back(there(start)) == start
  def isAdjoint[A,B](start: A, there: A => B, back: B => A)(rel: (A,A) => Boolean): Boolean = {
    val dest = there(start)
    val bc2 = back(dest)
    rel(start,bc2)
  }

  // f(x) binOp f(y) == f(x binOp y)
  def preserveOp[X,Y](x: X, y: X, f: X => X, binOp: (X,X) => X): Boolean = {
    val eq: (X,X) => Boolean = _ == _
    preserveOp[X,X](x, y, f, binOp, binOp, eq)
  }

  // f(p1) binOpY f(p2) <= f(p1 binOpX p2)
  def preserveOp[X,Y](x: X, y: X, f: X => Y, binOpX: (X,X) => X, binOpY: (Y,Y) => Y, rel: (Y,Y) => Boolean): Boolean =
    rel( binOpY( f(x), f(y) ), f(binOpX(x,y)) )
}
