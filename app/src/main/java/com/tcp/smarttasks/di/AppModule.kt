package com.tcp.smarttasks.di

import com.tcp.smarttasks.core.data.networking.HttpClientFactory
import com.tcp.smarttasks.tasks.data.networking.RemoteTasksDataSource
import com.tcp.smarttasks.tasks.domain.TasksDataSource
import com.tcp.smarttasks.tasks.presentation.task_detail.TaskDetailViewModel
import com.tcp.smarttasks.tasks.presentation.tasks_list.TasksViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteTasksDataSource).bind<TasksDataSource>()

    viewModelOf(::TasksViewModel)
    viewModelOf(::TaskDetailViewModel)
}