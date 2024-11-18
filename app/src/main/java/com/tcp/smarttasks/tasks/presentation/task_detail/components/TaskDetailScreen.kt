package com.tcp.smarttasks.tasks.presentation.task_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tcp.smarttasks.R
import com.tcp.smarttasks.tasks.presentation.task_detail.TaskDetailAction
import com.tcp.smarttasks.tasks.presentation.task_detail.TaskDetailState
import com.tcp.smarttasks.tasks.presentation.tasks_list.models.TaskUi
import com.tcp.smarttasks.tasks.presentation.tasks_list.models.formatDueDate
import com.tcp.smarttasks.ui.theme.AmsiPro
import com.tcp.smarttasks.ui.theme.DarkYellow
import com.tcp.smarttasks.ui.theme.DarkYellowBackground
import com.tcp.smarttasks.ui.theme.LightGreen
import com.tcp.smarttasks.ui.theme.LightRed
import com.tcp.smarttasks.ui.theme.LightYellow
import com.tcp.smarttasks.ui.theme.SmartTasksTheme
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun TaskDetailScreen(
    state: TaskDetailState,
    taskUi: TaskUi,
    onBackClick: () -> Unit,
    onResolveClick: (TaskDetailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val openDialog = remember { mutableStateOf(false) }
    val resolvedClicked = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .background(
                if (isSystemInDarkTheme()) DarkYellow
                else LightYellow
            )
    ) {
        TaskDetailTitle(onBackClick = onBackClick)
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(R.drawable.task_details),
                contentDescription = stringResource(R.string.task_detail),
                modifier = modifier
                    .fillMaxWidth()
                    .size(300.dp)
            )
            Text(
                text = taskUi.title,
                color = if (state.resolved)
                    LightGreen else LightRed,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Start,
                lineHeight = 30.sp,
                maxLines = 2,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 16.dp, bottom = 8.dp),
            )
            HorizontalDivider(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp, start = 16.dp, end = 16.dp),
                thickness = 1.dp,
                color = DarkYellowBackground
            )
            Column(
                modifier = modifier
                    .padding(top = 100.dp, start = 16.dp, end = 16.dp)
            ) {
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
                        color = if (state.resolved)
                            LightGreen else LightRed,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.weight(1F)
                    )
                    Text(
                        text = if (taskUi.dueDate != null)
                            ChronoUnit.DAYS.between(
                                taskUi.targetDate,
                                taskUi.dueDate
                            ).toString() else stringResource(R.string.unknown),
                        color = if (state.resolved)
                            LightGreen else LightRed,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            HorizontalDivider(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 160.dp, start = 16.dp, end = 16.dp),
                thickness = 1.dp,
                color = DarkYellowBackground
            )
            Text(
                text = taskUi.description,
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                maxLines = 3,
                lineHeight = 16.sp,
                modifier = modifier.padding(
                    top = 170.dp, start = 16.dp, end = 16.dp
                )
            )
            Text(
                text = if (state.resolved || state.cantResolve)
                    stringResource(R.string.resolved)
                else
                    stringResource(R.string.unresolved),
                textAlign = TextAlign.Start,
                color = if (state.resolved)
                    LightGreen
                else if (state.cantResolve)
                    LightRed
                else
                    LightYellow,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 260.dp, start = 16.dp, end = 16.dp
                    )
            )
        }
        if (state.unresolved) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .weight(1F),
                    onClick = {
                        openDialog.value = true
                        resolvedClicked.value = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightGreen
                    ),
                    shape = RectangleShape,
                ) {
                    Text(
                        text = stringResource(R.string.resolve),
                        fontSize = 16.sp,
                        fontFamily = AmsiPro,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
                VerticalDivider(
                    modifier = modifier
                        .padding(4.dp),
                    thickness = 0.dp,
                    color = if (isSystemInDarkTheme()) DarkYellow else LightYellow
                )
                Button(
                    modifier = modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .weight(1F),
                    onClick = {
                        openDialog.value = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightRed
                    ),
                    shape = RectangleShape,
                ) {
                    Text(
                        text = stringResource(R.string.cant_resolve),
                        fontSize = 16.sp,
                        fontFamily = AmsiPro,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
        } else {
            Icon(
                painter = if (state.resolved)
                    painterResource(R.drawable.sign_resolved)
                else
                    painterResource(R.drawable.unresolved_sign),
                contentDescription = null,
                tint = if (state.resolved)
                    LightGreen
                else LightRed,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
                    .size(150.dp)
            )
        }
        Spacer(modifier = modifier.size(20.dp))
        Text(text = taskUi.commentSent?.let {
            taskUi.commentSent
        } ?: "",
            color = if (state.resolved) LightGreen else LightRed,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )
    }
    if (openDialog.value) {
        TaskDialog(
            onDismissRequest = {
                openDialog.value = false
                if (resolvedClicked.value) {
                    onResolveClick(TaskDetailAction.OnResolvedClick(taskUi, resolvedClicked.value))
                } else {
                    onResolveClick(TaskDetailAction.OnCantResolveClick(taskUi, resolvedClicked.value))
                }
            },
            onClickingYes = {
                openDialog.value = false
                onResolveClick(TaskDetailAction.OnAddComment(taskUi, resolvedClicked.value))
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailScreenPreview() {
    SmartTasksTheme {
        TaskDetailScreen(
            taskUi = TaskUi(
                id = "",
                title = "Android job app assignment",
                description = "Create an application that will show tasks created from managers for employees with the list of tasks and task details",
                targetDate = LocalDate.now(),
                dueDate = LocalDate.now(),
                priority = 1,
                commentSent = "Comment sent to manager"
            ),
            onBackClick = {},
            onResolveClick = {},
            state = TaskDetailState(
                unresolved = false,
                resolved = true,
                cantResolve = false
            )
        )
    }
}
