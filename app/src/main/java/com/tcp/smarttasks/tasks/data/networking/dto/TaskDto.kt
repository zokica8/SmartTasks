package com.tcp.smarttasks.tasks.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    val id: String,
    @SerialName("TargetDate") val targetDate: String,
    @SerialName("DueDate") val dueDate: String? = null,
    @SerialName("Title") val title: String,
    @SerialName("Description") val description: String,
    @SerialName("Priority") val priority: Int = 0
)
