package br.com.sailboat.homeinventory.domain.usecase

@SuppressWarnings
abstract class UseCase<in Params, out Type> where Type : Any {

    abstract fun execute(params: Params): Type

}