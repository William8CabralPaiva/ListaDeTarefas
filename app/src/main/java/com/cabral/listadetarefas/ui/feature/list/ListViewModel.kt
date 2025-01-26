package com.cabral.listadetarefas.ui.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabral.listadetarefas.data.TodoRepository
import com.cabral.listadetarefas.ui.UIEvent
import com.cabral.listadetarefas.ui.navigation.AddEditRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val todos = repository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // o Channel é usado para se comunicar com a Ui
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.Delete -> {
                delete(id = event.id)
            }

            is ListEvent.CompleteChanged -> {
                completeChanged(event.id, event.isCompleted)
            }

            is ListEvent.AddEdit -> {
                navigateAddEdit(event.id)
            }
        }

    }

    private fun completeChanged(id: Long, isCompleted: Boolean) {
        viewModelScope.launch {
            repository.updateCompleted(id, isCompleted)
        }
    }

    private fun delete(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    private fun navigateAddEdit(id: Long?) {
        viewModelScope.launch {
            _uiEvent.send(UIEvent.Navigate(AddEditRoute(id)))
        }
    }

}