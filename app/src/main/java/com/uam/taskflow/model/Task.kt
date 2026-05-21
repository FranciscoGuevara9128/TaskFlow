package com.uam.taskflow.model

data class Task(
    val id: Int,
    val titulo: String,
    val prioridad: Int,
    val estado: Boolean
)
