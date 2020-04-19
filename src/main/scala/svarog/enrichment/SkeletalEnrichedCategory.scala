package svarog.enrichment

trait SkeletalEnrichedCategory[V,X]
  extends EnrichedCategory[V,X]

trait SkeletalEnrichedCategoryLaws
  extends EnrichedCategoryLaws {

  def skeletality[V,X](a: X, b: X)(implicit P: EnrichedCategory[V,X]): Boolean =
    if( P.base.le(P.base.I, P.homObject(a,b)) &&
      P.base.le(P.base.I, P.homObject(b,a)) ) a == b
    else true
}