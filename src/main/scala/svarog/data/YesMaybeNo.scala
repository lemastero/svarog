package svarog.data

import svarog.preorders.SymmetricMonoidalPreorder
import svarog.sets.PowerSet

object YesMaybeNo {
  sealed trait YMN
  case object Yes extends YMN
  case object Maybe extends YMN
  case object No extends YMN

  val SMP: SymmetricMonoidalPreorder[YMN] = new SymmetricMonoidalPreorder[YMN] {
    override def I: YMN = No
    override def multiply(a: YMN, b: YMN): YMN = (a,b) match {
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
    override def le(a: YMN, b: YMN): Boolean = (a,b) match {
      case (a, b) if a == b => true
      case (No, _) => true
      case (Maybe, No) => false
      case (Maybe, _) => true
      case (Yes, _) => false
    }
  }

  val powerSet: PowerSet[YMN] = new PowerSet[YMN](
    Set(
      Set(Yes), Set(Maybe), Set(No),
      Set(Yes, Maybe), Set(Yes, No), Set(Maybe, No),
      Set(Yes, Maybe, No)
    )
  )
}
