package com.cabral.listadetarefas.model

data class Todo(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean
)

//fake objects
val todo1 = Todo(1, "Title 1", "Description 1", false)
val todo2 = Todo(2, "Title 2", "Description 2", true)
val todo3 = Todo(3, "Title 3", "Description 3", false)
