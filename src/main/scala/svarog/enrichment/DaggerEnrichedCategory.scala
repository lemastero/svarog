package svarog.enrichment


trait DaggerEnrichedCategory[V,X]
  extends EnrichedCategory[V,X]

trait DaggerEnrichedCategoryLaws
  extends EnrichedCategoryLaws {

  def symmetry[V,X](ec: EnrichedCategory[V,X],a: X, b: X): Boolean =
    ec.homObject(a,b) == ec.homObject(b,a)
}
