package com.cabral.listadetarefas.data.di

import com.cabral.listadetarefas.data.LocalDataSource
import com.cabral.listadetarefas.data.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun bindLocalDataSource(dataSource: LocalDataSourceImpl): LocalDataSource

}