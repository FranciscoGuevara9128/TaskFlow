package com.uam.taskflow.vmodel

import com.uam.taskflow.model.Task
import com.uam.taskflow.repository.TaskRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    var tareas by mutableStateOf(listOf<Task>())
        private set

    var id by mutableStateOf("")
        private set
    var titulo by mutableStateOf("")
        private set
    var prioridad by mutableStateOf("")
        private set
    var estado by mutableStateOf(false)
        private set

    init {
        loadTareas()
    }

    // Funciones para actualizar los estados desde la Vista (UI)
    fun onIdChange(newId: String) {
        this.id = newId
    }
    fun onTituloChange(newTitulo: String) {
        this.titulo = newTitulo
    }
    fun onPrioridadChange(newPrioridad: String) {
        this.prioridad = newPrioridad
    }
    fun onEstadoChange(newEstado: Boolean) {
        this.estado = newEstado
    }

    private fun loadTareas() {
        tareas = repository.getTareas()
    }

    // Carga los datos de una tarea específica en el formulario para editarla
    fun loadTask(taskId: Int?) {
        if (taskId == null || taskId == -1) {
            clearForm()
            return
        } else {
            val task = repository.getTaskById(taskId)
            task?.let {
                id = it.id.toString()
                titulo = it.titulo
                prioridad = it.prioridad.toString()
                estado = it.estado
            }
        }
    }

    // Guarda una tarea nueva o edita una existente
    fun guardarTarea() {
        if (titulo.isBlank() || prioridad.isBlank()) return

        val idActual = id.toIntOrNull()
        val prioridadEntero = prioridad.toIntOrNull() ?: 1

        if (idActual == null) {
            val tareasReales = repository.getTareas()
            val nuevoId = (tareasReales.maxOfOrNull { it.id } ?: 0) + 1

            val nuevaTarea = Task(nuevoId, titulo, prioridadEntero, estado)
            repository.addTask(nuevaTarea)
        } else {
            repository.editTask(idActual, titulo, prioridadEntero, estado)
        }

        loadTareas()
        clearForm()
    }

    // Elimina una tarea de la lista
    fun removeTask(tarea: Task) {
        repository.removeTask(tarea)
        loadTareas()
    }

    fun getTaskById(id: Int): Task? {
        return repository.getTaskById(id)
    }

    // Restablece los campos del formulario a su estado inicial
    fun clearForm() {
        id = ""
        titulo = ""
        prioridad = ""
        estado = false
    }
}