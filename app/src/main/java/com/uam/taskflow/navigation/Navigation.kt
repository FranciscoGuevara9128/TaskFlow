package com.uam.taskflow.navigation

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
object TaskList

@Serializable
data class TaskDetail(val taskId: Int)

