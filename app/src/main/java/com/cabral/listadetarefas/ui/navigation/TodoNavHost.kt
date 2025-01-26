package com.cabral.listadetarefas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cabral.listadetarefas.ui.feature.addedit.AddEditScreen
import com.cabral.listadetarefas.ui.feature.list.ListScreen
import kotlinx.serialization.Serializable

@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Composable
fun TodoNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListRoute) {
        composable<ListRoute> {
            ListScreen(
                navigateAddEditScreen = { id ->
                    navController.navigate(AddEditRoute(id = id))
                })
        }

        composable<AddEditRoute> {
            //val addEditRoute = it.toRoute<AddEditRoute>()
            //NOTE com isso consigo capturar os dados passados
            AddEditScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}