package com.tcp.smarttasks.tasks.data.mappers

import com.tcp.smarttasks.tasks.data.networking.dto.TaskDto
import com.tcp.smarttasks.tasks.domain.Task
import java.time.LocalDate

fun TaskDto.toTask(): Task {
    return Task(
        id = id,
        targetDate = LocalDate.parse(targetDate),
        dueDate = if (dueDate != null)
            LocalDate.parse(dueDate) else null,
        title = title,
        description = description,
        priority = priority
    )
}