package com.tcp.smarttasks.tasks.presentation.task_detail

import com.tcp.smarttasks.tasks.presentation.tasks_list.models.TaskUi

data class TaskDetailState(
    val taskUi: TaskUi? = null,
    val unresolved: Boolean = true,
    val resolved: Boolean = false,
    val cantResolve: Boolean = false,
)
