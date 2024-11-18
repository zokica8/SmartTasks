package com.tcp.smarttasks.tasks.presentation.tasks_list

import com.tcp.smarttasks.core.domain.util.NetworkError

sealed interface TasksListEvent {
    data class Error(val error: NetworkError): TasksListEvent
}