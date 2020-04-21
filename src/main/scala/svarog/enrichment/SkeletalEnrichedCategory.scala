package svarog.enrichment

import svarog.{EquationalLaws, Equivalence}

trait SkeletalEnrichedCategory[V,X]
  extends EnrichedCategory[V,X]
  with Equivalence[X] {

  def homRelation(a: X, b: X): Boolean =
    base.le(base.I, homObject(a,b))

  override def equivalent(a: X, b: X): Boolean =
    homRelation(a,b) && homRelation(b,a)
}

trait SkeletalEnrichedCategoryLaws
  extends EnrichedCategoryLaws {

  def skeletality[V,X](a: X, b: X)(implicit P: SkeletalEnrichedCategory[V,X]): Boolean =
    EquationalLaws.skeletality(a,b, P.homRelation)
}