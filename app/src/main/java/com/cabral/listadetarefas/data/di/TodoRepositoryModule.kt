package com.cabral.listadetarefas.data.di

import com.cabral.listadetarefas.data.TodoRepository
import com.cabral.listadetarefas.data.TodoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)//segue o ciclo de vida da viewModel
interface TodoRepositoryModule {

    //a classe que precisar do todoReposiory ira receber o todoRepositoryImpl
    //devo utilizar o binds quando for uma implementação minha de uma interface
    //caso contrario se não for minha como o retrofit uso provides
    @Binds
    fun bindTodoRepository(repository: TodoRepositoryImpl): TodoRepository
}