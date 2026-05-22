package com.uam.taskflow.navigation

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object TaskList

@Serializable
data class TaskDetail(val taskId: Int)

