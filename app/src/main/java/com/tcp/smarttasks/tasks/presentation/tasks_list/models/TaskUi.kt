package com.tcp.smarttasks.tasks.presentation.tasks_list.models

import com.tcp.smarttasks.tasks.domain.Task
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class TaskUi(
    val id: String,
    val targetDate: LocalDate,
    val dueDate: LocalDate?,
    val title: String,
    val description: String,
    val priority: Int,
    val commentSent: String? = null,
    var taskStatusIcon: Int = 0,
    var unresolved: Boolean = true,
    var resolved: Boolean = false,
    var cantResolve: Boolean = false,
)

fun Task.toTaskUi(): TaskUi {
    return TaskUi(
        id = id,
        targetDate = targetDate,
        dueDate = dueDate,
        title = title,
        description = description,
        priority = priority,
    )
}

fun formatDueDate(localDate: LocalDate?): String? =
    localDate?.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) ?: ""
