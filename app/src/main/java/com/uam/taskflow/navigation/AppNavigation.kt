package com.uam.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.uam.taskflow.vmodel.TaskViewModel



@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TaskList,
        modifier = modifier
    ) {
        // 1. Pantalla del Listado de Tareas
        composable<TaskList> { backStackEntry ->
            val taskViewModel: TaskViewModel = viewModel(backStackEntry)

            TaskListScreen(
                navController = navController,
                viewModel = taskViewModel
            )
        }

        // 2. Pantalla de Detalle / Formulario de Tarea
        composable<TaskDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<TaskDetail>()

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(TaskList)
            }
            val taskViewModel: TaskViewModel = viewModel(parentEntry)

            TaskDetailScreen(
                navController = navController,
                taskId = route.taskId,
                viewModel = taskViewModel
            )
        }
    }
}