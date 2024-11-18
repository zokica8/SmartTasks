package com.tcp.smarttasks.tasks.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class TasksResponseDto(
    val tasks: List<TaskDto>
)
