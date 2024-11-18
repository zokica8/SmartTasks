package com.tcp.smarttasks.tasks.presentation.task_detail

import androidx.lifecycle.ViewModel
import com.tcp.smarttasks.tasks.presentation.tasks_list.models.TaskUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskDetailViewModel : ViewModel() {

    private val _state = MutableStateFlow(TaskDetailState())
    val state = _state.asStateFlow()

    fun onAction(action: TaskDetailAction) {
        when (action) {
            is TaskDetailAction.OnResolvedClick -> {
                _state.update {
                    it.copy(
                        taskUi = action.taskUi,
                        unresolved = false,
                        resolved = action.resolvedClicked,
                    )
                }
            }

            is TaskDetailAction.OnCantResolveClick -> {
                _state.update {
                    it.copy(
                        taskUi = action.taskUi,
                        unresolved = false,
                        cantResolve = !action.resolvedClicked
                    )
                }
            }

            is TaskDetailAction.OnAddComment -> {
                _state.update {
                    it.copy(
                        taskUi = action.taskUi,
                        unresolved = false,
                        resolved = action.resolvedClicked,
                        cantResolve = !action.resolvedClicked
                    )
                }
            }
        }
    }

    fun setSelectedTask(taskUi: TaskUi) {
        _state.update {
            it.copy(
                taskUi = taskUi,
                unresolved = taskUi.unresolved,
                resolved = taskUi.resolved,
                cantResolve = taskUi.cantResolve
            )
        }
    }

    fun resetState(taskStatusIcon: Int, unresolved: Boolean) {
        if (unresolved && taskStatusIcon == 0) {
            _state.update {
                it.copy(
                    taskUi = null,
                    unresolved = true,
                    resolved = false,
                    cantResolve = false
                )
            }
        }
    }
}