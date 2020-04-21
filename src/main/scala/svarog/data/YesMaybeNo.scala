package svarog.data

import svarog.preorders.SymmetricMonoidalPreorder
import svarog.sets.PowerSet

object YesMaybeNo {
  sealed trait NMY
  case object Yes extends NMY
  case object Maybe extends NMY
  case object No extends NMY

  val `(NMY, ≤, yes, min)`: SymmetricMonoidalPreorder[NMY] = new SymmetricMonoidalPreorder[NMY] {
    override def I: NMY = No
    override def multiply(a: NMY, b: NMY): NMY = (a,b) match {
      case (a, b) if a == b => a
      case (No, _) => No
      case (Maybe, No) => No
      case (Maybe, _) => Maybe
      case (Yes, other) => other
    }

    /**
     *  min   | No Maybe Yes
     *  ---------------------
     *  No    | N  M     Y
     *  Maybe | N  M     Y
     *  Yes   | N  M     Y
     */
    override def le(a: NMY, b: NMY): Boolean = (a,b) match {
      case (a, b) if a == b => true
      case (No, _) => true
      case (Maybe, No) => false
      case (Maybe, _) => true
      case (Yes, _) => false
    }
  }

  val powerSet: PowerSet[NMY] = new PowerSet[NMY](
    Set(
      Set(Yes), Set(Maybe), Set(No),
      Set(Yes, Maybe), Set(Yes, No), Set(Maybe, No),
      Set(Yes, Maybe, No)
    )
  )
}
