package com.tcp.smarttasks.tasks.presentation.tasks_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tcp.smarttasks.R
import com.tcp.smarttasks.tasks.domain.Task
import com.tcp.smarttasks.tasks.presentation.tasks_list.models.TaskUi
import com.tcp.smarttasks.tasks.presentation.tasks_list.models.formatDueDate
import com.tcp.smarttasks.tasks.presentation.tasks_list.models.toTaskUi
import com.tcp.smarttasks.ui.theme.DarkYellowBackground
import com.tcp.smarttasks.ui.theme.LightGreen
import com.tcp.smarttasks.ui.theme.LightRed
import com.tcp.smarttasks.ui.theme.LightYellowBackground
import com.tcp.smarttasks.ui.theme.SmartTasksTheme
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun TasksListItem(
    taskUi: TaskUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(8.dp))
            .background(LightYellowBackground)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = taskUi.title,
            color = LightRed,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            lineHeight = 35.sp,
        )
        if (taskUi.taskStatusIcon != 0) {
            Icon(
                painter = painterResource(taskUi.taskStatusIcon),
                tint = Color.Black,
                contentDescription = null,
                modifier = modifier.size(20.dp)
            )
        }
        HorizontalDivider(
            modifier = modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DarkYellowBackground
        )
        Column(modifier = modifier) {
            Row(modifier = modifier) {
                Text(
                    text = stringResource(R.string.due_date),
                    modifier = modifier.weight(1F)
                )
                Text(
                    text = stringResource(R.string.days_left),
                    textAlign = TextAlign.End
                )
            }
            Row(modifier = modifier) {
                Text(
                    text = formatDueDate(taskUi.dueDate?.let {
                        taskUi.dueDate
                    }) ?: stringResource(R.string.unknown),
                    color = LightRed,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.weight(1F)
                )
                Text(
                    text = if (taskUi.dueDate != null)
                        ChronoUnit.DAYS.between(
                            taskUi.targetDate,
                            taskUi.dueDate
                        ).toString() else stringResource(R.string.unknown),
                    color = LightRed,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TasksListItemPreview() {
    SmartTasksTheme {
        TasksListItem(
            taskUi = previewTask,
            onClick = {},
        )
    }
}

internal val previewTask = Task(
    id = "2222",
    targetDate = LocalDate.parse("2024-11-19"),
    dueDate = LocalDate.parse("2024-12-11"),
    title = "Android job app assignment",
    description = "Create an application that will show tasks created from managers for employees with the list of tasks and task details",
    priority = 1,
).toTaskUi()