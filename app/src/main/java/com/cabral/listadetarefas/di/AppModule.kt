package com.cabral.listadetarefas.di

import com.cabral.listadetarefas.data.TodoDatabase
import com.cabral.listadetarefas.data.TodoDatabaseProvider
import com.cabral.listadetarefas.data.TodoRepository
import com.cabral.listadetarefas.data.TodoRepositoryImpl
import com.cabral.listadetarefas.ui.feature.addedit.AddEditViewModel
import com.cabral.listadetarefas.ui.feature.list.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val module = module {
        single { TodoDatabaseProvider.provide(androidContext()) }
        single { get<TodoDatabase>().dao }
        single<TodoRepository> { TodoRepositoryImpl(get()) }
        viewModel { AddEditViewModel(get()) }
        viewModel { ListViewModel(get()) }
    }
}