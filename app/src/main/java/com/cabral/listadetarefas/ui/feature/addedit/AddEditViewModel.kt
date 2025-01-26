package com.cabral.listadetarefas.ui.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.cabral.listadetarefas.data.TodoRepository
import com.cabral.listadetarefas.ui.UIEvent
import com.cabral.listadetarefas.ui.navigation.AddEditRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TodoRepository
) : ViewModel() {

    private val addEditRoute = savedStateHandle.toRoute<AddEditRoute>()

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf<String?>(null)
        private set

    init {
        addEditRoute.id?.let {
            viewModelScope.launch {
                repository.getBy(it)?.let { todo ->
                    title = todo.title
                    description = todo.description
                }
            }
        }
    }

    // o Channel é usado para se comunicar com a Ui
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.TitleChanged -> {
                title = event.title
            }

            is AddEditEvent.DescriptionChanged -> {
                description = event.description
            }

            is AddEditEvent.Save -> {
                saveTodo()
            }
        }
    }

    private fun saveTodo() {
        viewModelScope.launch {
            if (title.isBlank()) {
                _uiEvent.send(UIEvent.ShowSnackBar("O título não pode estar em branco"))
            } else {
                repository.insert(title, description, addEditRoute.id)
                _uiEvent.send(UIEvent.NavigateBack)
            }
        }
    }


}