package com.uam.taskflow.repository

import com.uam.taskflow.model.Task
import java.util.concurrent.CopyOnWriteArrayList

class TaskRepository {
    private val tareas = CopyOnWriteArrayList<Task>(
        listOf(
            Task(1, "Entregar tarea de finanzas", 3, false),
            Task(2, "Estudiar para examen de física", 2, true),
            Task(3, "Completar examen de POO", 1, false)
        )
    )

    // READ]
    fun getTareas(): List<Task> = tareas.toList()

    // CREATE
    fun addTask(tarea: Task): Boolean = tareas.add(tarea)

    // READ
    fun getTaskById(id: Int): Task? = tareas.find { it.id == id }

    // UPDATE
    fun editTask(id: Int, nuevoTitulo: String, nuevaPrioridad: Int, nuevoEstado: Boolean): Boolean {
        val tareaOriginal = tareas.find { it.id == id } ?: return false
        val index = tareas.indexOf(tareaOriginal)

        if (index != -1) {
            // Reemplazo de la tarea vieja con la copia modificada
            tareas[index] = tareaOriginal.copy(
                titulo = nuevoTitulo,
                prioridad = nuevaPrioridad,
                estado = nuevoEstado
            )
            return true
        }
        return false
    }

    // DELETE
    fun removeTask(tarea: Task): Boolean = tareas.remove(tarea)
}