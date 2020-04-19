package svarog.enrichment

import svarog.data.YesMaybeNo
import svarog.data.YesMaybeNo.YMN
import svarog.preorders.SymmetricMonoidalPreorder

// Half open circuit? Circuit breaker?
trait YMNCategory[X] extends EnrichedCategory[YMN,X] {
  def base: SymmetricMonoidalPreorder[YMN] = YesMaybeNo.SMP
}
