package br.com.sailboat.homeinventory.domain.failure

sealed class Failure {
    class NetworkConnection : Failure()
    class ServerError : Failure()

    abstract class FeatureFailure : Failure()
}