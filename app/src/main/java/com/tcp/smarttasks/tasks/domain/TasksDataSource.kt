package com.tcp.smarttasks.tasks.domain

import com.tcp.smarttasks.core.domain.util.Result
import com.tcp.smarttasks.core.domain.util.NetworkError

interface TasksDataSource {
    suspend fun getTasks(): Result<List<Task>, NetworkError>
}