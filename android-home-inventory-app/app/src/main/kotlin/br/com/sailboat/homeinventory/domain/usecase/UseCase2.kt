package br.com.sailboat.homeinventory.domain.usecase

abstract class UseCase2<in Params, out Type> where Type : Any {

    abstract fun execute(params: Params): Type

}