package svarog.category_theory

import svarog.set_theory.MathSet
import svarog.universal_algebra.Equality

trait Graph[V] {
  type Path[A <: V,B <: V] = List[V]
  type Edge = (V,V)
  def vertices: MathSet[V]
  def edges: MathSet[Edge]
  def startOfPath[A <: V,B <: V](p: Path[A,B]): A = p.head
  def path[A <: V,B <: V](a: V, b: V): MathSet[Path[A,B]]
  def trivialPath[A <: V](a: V): Path[V,V] = List(a)
  def concatPaths[A <: V, B <: V, C <: V](p1: Path[A,B], p2: Path[B,C]): Path[A,C] = p1 ++ p2
}

object Graph {
  def FreeCategory[V](g: Graph[V]): SmallCategory[V, g.Path] = new SmallCategory[V, g.Path] {
    override def objects: MathSet[V] = g.vertices
    override def homSet[A <: V, B <: V](a: A, b: B): MathSet[g.Path[A, B]] = g.path(a,b)
    override def domain[A <: V, B <: V](f: g.Path[A, B]): A = f.head
    override def codomain[A <: V, B <: V](f: g.Path[A, B]): B = f.last
    override def identjty[A <: V](a: A): g.Path[A, A] = g.trivialPath(a)
    override def composite[A <: V, B <: V, C <: V](f: g.Path[A, B], g: g.Path[B, C]): g.Path[A, C] = ???
  }
}

trait SmallCategory[Obj, Morph[_,_]] {
  def objects: MathSet[Obj]

  def homSet[A <: Obj,B <: Obj](a: A, b: B): MathSet[Morph[A,B]]
  def domain[A <: Obj,B <: Obj](f: Morph[A, B]): A
  def codomain[A <: Obj,B <: Obj](f: Morph[A, B]): B

  def identjty[A <: Obj](a: A): Morph[A,A]
  def composite[A <: Obj,B <: Obj,C <: Obj](f: Morph[A,B], g: Morph[B,C]): Morph[A,C]
}

trait SmallCategoryLaws {

  def unitality[Obj, A <: Obj,B <: Obj, Morph[_,_]](
      f: Morph[A,B])(
      cat: SmallCategory[Obj,Morph],
      eq: Equality[Morph[A,B]]): Boolean = {
    val a: A = cat.domain(f)
    val b: B = cat.codomain(f)
    val idb: Morph[B,B] = cat.identjty(b)
    val ida: Morph[A, A] = cat.identjty(a)
    eq.isEq(cat.composite(f,idb), f) && eq.isEq(cat.composite(ida, f), f)
  }

  def associativity[Obj, A <: Obj, B <: Obj, C <: Obj, D <: Obj, Morph[_,_]](
      f: Morph[A,B], g: Morph[B,C], h: Morph[C,D])(
      cat: SmallCategory[Obj,Morph],
      eq: Equality[Morph[A,D]]): Boolean = {
    val fg = cat.composite(f,g)
    val gh = cat.composite(g,h)
    eq.isEq(cat.composite(fg,h), cat.composite(f,gh))
  }
}
