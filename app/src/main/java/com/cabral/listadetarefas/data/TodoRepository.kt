package com.cabral.listadetarefas.data

import com.cabral.listadetarefas.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insert(title: String, description: String?,id: Long? = null)

    suspend fun updateCompleted(id: Long, isCompleted: Boolean)

    suspend fun delete(id: Long)

    fun getAll(): Flow<List<Todo>>

    suspend fun getBy(id: Long): Todo?
}