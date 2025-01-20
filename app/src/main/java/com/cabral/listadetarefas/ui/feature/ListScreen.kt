package com.cabral.listadetarefas.ui.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cabral.listadetarefas.model.Todo
import com.cabral.listadetarefas.model.todo1
import com.cabral.listadetarefas.model.todo2
import com.cabral.listadetarefas.model.todo3
import com.cabral.listadetarefas.ui.components.TodoItem
import com.cabral.listadetarefas.ui.theme.ListaDeTarefasTheme

@Composable
fun ListScreen() {
    ListContent(todos = emptyList())
}

@Composable
fun ListContent(
    todos: List<Todo>
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(it),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(todos.size) { index ->
                TodoItem(
                    todo = todos[index],
                    onCompletedChange = {},
                    onItemClicked = {},
                    onDeleteClicked = {}
                )

                if (index < todos.lastIndex) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

}

@Preview
@Composable
private fun ListContentPreview() {
    ListaDeTarefasTheme {
        val list = listOf(todo1, todo2, todo3)
        ListContent(list)
    }

}