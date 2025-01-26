package com.cabral.listadetarefas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TodoEntity::class],
    version = 1,
)
abstract class TodoDatabase : RoomDatabase() {
    abstract val dao: TodoDao
}