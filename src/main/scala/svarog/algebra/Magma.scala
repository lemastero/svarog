package svarog.algebra

import simulacrum.{op, typeclass}

@typeclass
trait Magma[X] {
  @op("*") def multiply(a: X, b: X): X
}
