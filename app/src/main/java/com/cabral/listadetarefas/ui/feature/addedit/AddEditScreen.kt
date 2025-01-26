package com.cabral.listadetarefas.ui.feature.addedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cabral.listadetarefas.data.TodoRepositoryImpl
import com.cabral.listadetarefas.ui.UIEvent
import com.cabral.listadetarefas.ui.theme.ListaDeTarefasTheme

@Composable
fun AddEditScreen(
    viewModel: AddEditViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {

    //remember para não perder o estado do snackbar
    val snackBarHostState = remember { SnackbarHostState() }

    //serve para executar apenas uma unica vez na criação
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {

                is UIEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = it.message,
                    )
                }

                UIEvent.NavigateBack -> {
                    navigateBack()
                }

                is UIEvent.Navigate<*> -> {}
            }
        }
    }


    val title = viewModel.title
    val description = viewModel.description

    AddEditComponent(title, description, snackBarHostState, viewModel::onEvent)
}

@Composable
fun AddEditComponent(
    title: String,
    description: String?,
    snackbarHostState: SnackbarHostState,
    onEvent: (AddEditEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(AddEditEvent.Save)
            }) {
                Icon(Icons.Filled.Check, contentDescription = "Save")
            }
        }, snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(paddingValues)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                placeholder = { Text(text = "Titulo") },
                onValueChange = {
                    onEvent(AddEditEvent.TitleChanged(it))
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description ?: "",
                placeholder = { Text(text = "Descrição (opcional)") },
                onValueChange = {
                    onEvent(AddEditEvent.DescriptionChanged(it))
                },
            )
        }
    }
}

@Preview
@Composable
private fun AddEditComponentPreview() {
    ListaDeTarefasTheme {
        AddEditComponent(
            title = "",
            description = null,
            snackbarHostState = SnackbarHostState(),
            onEvent = {}
        )
    }
}