package com.tcp.smarttasks.tasks.domain

import java.time.LocalDate

data class Task(
    val id: String,
    val targetDate: LocalDate,
    val dueDate: LocalDate?,
    val title: String,
    val description: String,
    val priority: Int
)
