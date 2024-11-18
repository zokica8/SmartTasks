package com.tcp.smarttasks.tasks.presentation.tasks_list

import com.tcp.smarttasks.tasks.presentation.tasks_list.models.TaskUi

data class TasksListState(
    val isLoading: Boolean = false,
    val tasks: List<TaskUi> = emptyList(),
    val selectedTask: TaskUi? = null
)
