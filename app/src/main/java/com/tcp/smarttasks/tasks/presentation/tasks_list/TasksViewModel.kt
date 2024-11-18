package com.tcp.smarttasks.tasks.presentation.tasks_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcp.smarttasks.core.domain.util.onError
import com.tcp.smarttasks.core.domain.util.onSuccess
import com.tcp.smarttasks.tasks.domain.Task
import com.tcp.smarttasks.tasks.domain.TasksDataSource
import com.tcp.smarttasks.tasks.presentation.tasks_list.models.TaskUi
import com.tcp.smarttasks.tasks.presentation.tasks_list.models.toTaskUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class TasksViewModel(
    private val tasksDataSource: TasksDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(TasksListState())
    val state = _state
        .onStart { loadTasks() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            TasksListState()
        )

    private val _targetDate = MutableStateFlow(LocalDate.now())
    val targetDate = _targetDate.asStateFlow()

    private val _tasksList = MutableStateFlow(emptyList<Task>())

    private val _events = Channel<TasksListEvent>()
    val events = _events.receiveAsFlow()

    private fun loadTasks() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            tasksDataSource.getTasks()
                .onSuccess { tasks ->
                    _tasksList.update { tasks }
                    _state.update { it ->
                        it.copy(
                            isLoading = false,
                            tasks = tasks.map { it.toTaskUi() }
                                .filter { taskUi ->
                                    taskUi.targetDate.isEqual(_targetDate.value)
                                }
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(TasksListEvent.Error(error))
                }
        }
    }

    fun incrementTargetDate() {
        _targetDate.update {
            it.plusDays(1)
        }
        updateTasks()
    }

    fun decrementTargetDate() {
        _targetDate.update {
            it.minusDays(1)
        }
        updateTasks()
    }

    private fun updateTasks() {
        _state.update { it ->
            it.copy(
                tasks = _tasksList.value.map { it.toTaskUi() }
                    .filter { taskUi ->
                        taskUi.targetDate.isEqual(_targetDate.value)
                    }
                    .sortedBy { taskUi -> taskUi.priority }
            )
        }
    }

    fun onAction(action: TasksListAction) {
        when (action) {
            is TasksListAction.OnTaskClick -> {
                selectTask(action.taskUi)
            }
        }
    }

    private fun selectTask(taskUi: TaskUi) {
        _state.update { it.copy(selectedTask = taskUi) }
    }

    fun updateTask(taskStatusIcon: Int?,
                   taskId: String?,
                   unresolved: Boolean?,
                   resolved: Boolean?,
                   cantResolve: Boolean?) {
        _state.value.tasks.forEach { item ->
            if (item.id == taskId) {
                if (taskStatusIcon != null) {
                    item.taskStatusIcon = taskStatusIcon
                }
                item.unresolved = unresolved != null && unresolved
                item.resolved = resolved != null && resolved
                item.cantResolve = cantResolve != null && cantResolve

            }
        }
    }
}