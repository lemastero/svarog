package svarog.enrichment

import svarog.EquationalLaws

trait DaggerEnrichedCategory[V,X]
  extends EnrichedCategory[V,X]

trait DaggerEnrichedCategoryLaws
  extends EnrichedCategoryLaws {

  def symmetry[V,X](ec: EnrichedCategory[V,X],a: X, b: X): Boolean =
    EquationalLaws.symmetry(a,b,ec.homObject)
}
