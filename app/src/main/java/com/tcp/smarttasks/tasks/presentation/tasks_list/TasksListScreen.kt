package com.tcp.smarttasks.tasks.presentation.tasks_list

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tcp.smarttasks.R
import com.tcp.smarttasks.tasks.presentation.task_detail.TaskDetailState
import com.tcp.smarttasks.tasks.presentation.tasks_list.components.DateSelection
import com.tcp.smarttasks.tasks.presentation.tasks_list.components.NoTasksScreen
import com.tcp.smarttasks.tasks.presentation.tasks_list.components.TasksListItem
import com.tcp.smarttasks.tasks.presentation.tasks_list.components.previewTask
import com.tcp.smarttasks.ui.theme.DarkYellow
import com.tcp.smarttasks.ui.theme.LightRed
import com.tcp.smarttasks.ui.theme.LightYellow
import com.tcp.smarttasks.ui.theme.SmartTasksTheme
import java.time.LocalDate

@Composable
fun TasksListScreen(
    state: TasksListState,
    targetDate: LocalDate,
    onBackClick: () -> Unit,
    onForwardClick: () -> Unit,
    onAction: (TasksListAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    if (isSystemInDarkTheme()) DarkYellow
                    else LightYellow
                ),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = LightRed)
        }
    } else if (state.tasks.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .background(
                    if (isSystemInDarkTheme())
                        DarkYellow else LightYellow
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DateSelection(
                onBackClick = onBackClick,
                onForwardClick = onForwardClick,
                targetDate = targetDate
            )
            NoTasksScreen()
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .background(
                    if (isSystemInDarkTheme())
                        DarkYellow else LightYellow
                )
        ) {
            DateSelection(
                onBackClick = onBackClick,
                onForwardClick = onForwardClick,
                targetDate = targetDate
            )
            Spacer(modifier = modifier.size(30.dp))
            LazyColumn(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.tasks) { taskUi ->
                    TasksListItem(
                        taskUi = taskUi,
                        onClick = {
                            onAction(TasksListAction.OnTaskClick(taskUi))
                        },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TasksListScreenPreview() {
    SmartTasksTheme {
        TasksListScreen(
            state = TasksListState(
                tasks = (1..10).map {
                    previewTask.copy(id = it.toString())
                }
            ),
            onAction = {},
            onForwardClick = {},
            onBackClick = {},
            targetDate = LocalDate.now(),
        )
    }
}