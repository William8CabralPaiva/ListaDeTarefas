package com.cabral.listadetarefas.ui.feature.list

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cabral.listadetarefas.data.TodoDatabaseProvider
import com.cabral.listadetarefas.data.TodoRepositoryImpl
import com.cabral.listadetarefas.model.Todo
import com.cabral.listadetarefas.model.todo1
import com.cabral.listadetarefas.model.todo2
import com.cabral.listadetarefas.model.todo3
import com.cabral.listadetarefas.ui.UIEvent
import com.cabral.listadetarefas.ui.components.TodoItem
import com.cabral.listadetarefas.ui.feature.addedit.AddEditViewModel
import com.cabral.listadetarefas.ui.navigation.AddEditRoute
import com.cabral.listadetarefas.ui.theme.ListaDeTarefasTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    navigateAddEditScreen: (id: Long?) -> Unit
) {
    val viewModel: ListViewModel = koinViewModel()

    //transformar em estado
    //como esta com flow apenas de adicionar ja aparece na tela anterior não preciso tratar pra att a lista
    val todos by viewModel.todos.collectAsState()

    //serve para executar apenas uma unica vez na criação
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvent.Navigate<*> -> {
                   if (it.route is AddEditRoute) {
                       navigateAddEditScreen(it.route.id)
                   }
                }
                is UIEvent.ShowSnackBar -> {}
                UIEvent.NavigateBack -> {}
            }
        }
    }

    ListContent(
        todos = todos,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ListContent(
    todos: List<Todo>,
   onEvent: (ListEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(ListEvent.AddEdit(null)) }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(todos.size) { index ->
                TodoItem(
                    todo = todos[index],
                    onCompletedChange = {
                        onEvent(ListEvent.CompleteChanged(todos[index].id,it))
                    },
                    onItemClicked = {
                        onEvent(ListEvent.AddEdit(todos[index].id))
                    },
                    onDeleteClicked = {
                        onEvent(ListEvent.Delete(todos[index].id))
                    }
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
        ListContent(list, onEvent = {})
    }

}