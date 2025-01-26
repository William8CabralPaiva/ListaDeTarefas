package com.cabral.listadetarefas.data

import com.cabral.listadetarefas.data.database.TodoEntity
import com.cabral.listadetarefas.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : TodoRepository {

    override suspend fun insert(title: String, description: String?, id: Long?) {
        val entity = id?.let {
            localDataSource.getBy(it)?.copy(
                title = title,
                description = description
            )
        } ?: run {
            TodoEntity(
                title = title,
                description = description,
                isCompleted = false,
            )
        }

        localDataSource.insertOrUpdate(entity)
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean) {
        val existentEntity = localDataSource.getBy(id) ?: return
        val updatedEntity = existentEntity.copy(isCompleted = isCompleted)
        localDataSource.insertOrUpdate(updatedEntity)
    }

    override suspend fun delete(id: Long) {
        val existentEntity = localDataSource.getBy(id) ?: return
        localDataSource.delete(existentEntity)
    }

    override fun getAll(): Flow<List<Todo>> {
        return localDataSource.getAll().map {
            it.map { entity ->
                Todo(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    isCompleted = entity.isCompleted
                )
            }
        }
    }

    override suspend fun getBy(id: Long): Todo? {
        return localDataSource.getBy(id)?.let { entity ->
            Todo(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                isCompleted = entity.isCompleted
            )
        }
    }
}