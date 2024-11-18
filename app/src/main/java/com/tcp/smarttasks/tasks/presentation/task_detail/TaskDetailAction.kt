package com.tcp.smarttasks.tasks.presentation.task_detail

import com.tcp.smarttasks.tasks.presentation.tasks_list.models.TaskUi

sealed interface TaskDetailAction {
    data class OnResolvedClick(val taskUi: TaskUi, val resolvedClicked: Boolean) : TaskDetailAction
    data class OnCantResolveClick(val taskUi: TaskUi, val resolvedClicked: Boolean) : TaskDetailAction
    data class OnAddComment(val taskUi: TaskUi, val resolvedClicked: Boolean) : TaskDetailAction
}