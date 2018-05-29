package br.com.sailboat.homeinventory.domain.failure

class ProductFailure {
    class ListNotAvailable : Failure.FeatureFailure()
    class NonExistentProduct : Failure.FeatureFailure()
}