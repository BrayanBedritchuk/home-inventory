package br.com.sailboat.homeinventory

import br.com.sailboat.homeinventory.core.repository.RepositoryFactory

interface Dependencies {

    fun getRepository(): RepositoryFactory

}