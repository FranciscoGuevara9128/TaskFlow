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

    // READ
    // Devuelve las tareas ordenadas por prioridad ascendente (1 = mayor prioridad)
    fun getTareas(): List<Task> = tareas.toList().sortedBy { it.prioridad }

    // CREATE
    // Solo permite prioridades válidas: 1, 2 o 3
    fun addTask(tarea: Task): Boolean {
        if (tarea.prioridad !in 1..3) return false
        return tareas.add(tarea)
    }

    // READ
    fun getTaskById(id: Int): Task? = tareas.find { it.id == id }

    // UPDATE
    fun editTask(id: Int, nuevoTitulo: String, nuevaPrioridad: Int, nuevoEstado: Boolean): Boolean {
        // Validación de prioridad
        if (nuevaPrioridad !in 1..3) return false

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