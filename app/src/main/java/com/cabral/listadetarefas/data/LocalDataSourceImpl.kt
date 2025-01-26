package com.cabral.listadetarefas.data

import com.cabral.listadetarefas.data.database.TodoDao
import com.cabral.listadetarefas.data.database.TodoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val todoDao: TodoDao
):LocalDataSource {
    override suspend fun insertOrUpdate(entity: TodoEntity) {
        todoDao.insert(entity)
    }

    override suspend fun delete(entity: TodoEntity) {
       todoDao.delete(entity)
    }

    override fun getAll(): Flow<List<TodoEntity>> {
        return todoDao.getAll()
    }

    override suspend fun getBy(id: Long): TodoEntity? {
        return todoDao.getBy(id)
    }


}