package com.cabral.listadetarefas.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cabral.listadetarefas.model.Todo
import com.cabral.listadetarefas.model.todo1
import com.cabral.listadetarefas.model.todo2
import com.cabral.listadetarefas.ui.theme.ListaDeTarefasTheme

@Composable
fun TodoItem(
    todo: Todo,
    onCompletedChange: (Boolean) -> Unit,
    onItemClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
        onClick = onItemClicked
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = todo.isCompleted, onCheckedChange = onCompletedChange)

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleLarge
                )

                todo.description?.let {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onDeleteClicked) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete")
            }

        }
    }
}

@Preview
@Composable
private fun TodoItemPrev() {
    ListaDeTarefasTheme {
        TodoItem(todo1,
            onCompletedChange = {},
            onItemClicked = {},
            onDeleteClicked = {}
        )
    }
}

@Preview
@Composable
private fun TodoItemIsCompletedPrev() {
    ListaDeTarefasTheme {
        TodoItem(todo2,
            onCompletedChange = {},
            onItemClicked = {},
            onDeleteClicked = {}
        )
    }
}