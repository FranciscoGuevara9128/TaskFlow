package com.uam.taskflow.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlaylistAddCheck
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uam.taskflow.navigation.TaskList
import com.uam.taskflow.navigation.AppNavigation
import com.uam.taskflow.navigation.Splash
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    // Efecto lanzado al entrar a la pantalla
    LaunchedEffect(key1 = true) {
        delay(2000) // Espera de 2 segundos (2000 milisegundos)

        // Navega a la lista de tareas y limpia el Splash del historial
        navController.navigate(TaskList) {
            popUpTo(Splash) { inclusive = true }
        }
    }

    // Diseño visual de la pantalla de carga
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icono representativo de una app de tareas
            Icon(
                imageVector = Icons.Default.PlaylistAddCheck,
                contentDescription = "Logo de la App",
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Título de la aplicación
            Text(
                text = "Task Manager App",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Indicador de progreso circular
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}