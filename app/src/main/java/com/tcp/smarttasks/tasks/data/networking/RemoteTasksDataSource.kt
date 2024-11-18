package com.tcp.smarttasks.tasks.data.networking

import com.tcp.smarttasks.core.data.networking.buildUrl
import com.tcp.smarttasks.core.data.networking.safeCall
import com.tcp.smarttasks.core.domain.util.NetworkError
import com.tcp.smarttasks.core.domain.util.Result
import com.tcp.smarttasks.core.domain.util.map
import com.tcp.smarttasks.tasks.data.mappers.toTask
import com.tcp.smarttasks.tasks.data.networking.dto.TasksResponseDto
import com.tcp.smarttasks.tasks.domain.Task
import com.tcp.smarttasks.tasks.domain.TasksDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteTasksDataSource(
    private val httpClient: HttpClient
) : TasksDataSource {

    override suspend fun getTasks(): Result<List<Task>, NetworkError> {
        return safeCall<TasksResponseDto> {
            httpClient.get(
                urlString = buildUrl("")
            )
        }.map { response ->
            response.tasks.map {
                it.toTask()
            }
        }
    }
}