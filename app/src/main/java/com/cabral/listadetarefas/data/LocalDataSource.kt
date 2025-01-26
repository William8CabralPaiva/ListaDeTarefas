package com.cabral.listadetarefas.data

import com.cabral.listadetarefas.data.database.TodoEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertOrUpdate(entity: TodoEntity)

    suspend fun delete(entity: TodoEntity)

    fun getAll(): Flow<List<TodoEntity>>

    suspend fun getBy(id: Long): TodoEntity?
}