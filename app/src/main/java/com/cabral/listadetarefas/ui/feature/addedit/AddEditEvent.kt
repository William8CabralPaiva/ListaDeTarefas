package com.cabral.listadetarefas.ui.feature.addedit

sealed interface AddEditEvent {
    data class TitleChanged(val title: String) : AddEditEvent
    data class DescriptionChanged(val description: String) : AddEditEvent
    data class SetId(val id: Long?) : AddEditEvent
    data object Save : AddEditEvent
}