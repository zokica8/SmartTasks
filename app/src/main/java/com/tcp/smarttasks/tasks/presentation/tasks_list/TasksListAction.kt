package com.tcp.smarttasks.tasks.presentation.tasks_list

import com.tcp.smarttasks.tasks.presentation.tasks_list.models.TaskUi

sealed interface TasksListAction {
    data class OnTaskClick(val taskUi: TaskUi) : TasksListAction
}