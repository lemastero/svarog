package svarog.enrichment

import svarog.universal_algebra.EquationalLaws

trait DaggerEnrichedCategory[V,X]
  extends EnrichedCategory[V,X]

trait DaggerEnrichedCategoryLaws
  extends EnrichedCategoryLaws {

  def symmetry[V,X](ec: EnrichedCategory[V,X],a: X, b: X): Boolean =
    EquationalLaws.symmetry(a,b,ec.homObject)
}
