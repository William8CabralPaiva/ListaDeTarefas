package com.cabral.listadetarefas.data.database.di

import android.content.Context
import androidx.room.Room
import com.cabral.listadetarefas.data.database.TodoDao
import com.cabral.listadetarefas.data.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RunDataBaseModule {

    @Provides
    @Singleton
    fun provideRunDataBase(
        @ApplicationContext context: Context
    ): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todo-app"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(
        database: TodoDatabase
    ): TodoDao {
        return database.dao
    }
}