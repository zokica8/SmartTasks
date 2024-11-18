package com.tcp.smarttasks.core.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tcp.smarttasks.R
import com.tcp.smarttasks.core.presentation.util.ObserveAsEvents
import com.tcp.smarttasks.core.presentation.util.toString
import com.tcp.smarttasks.tasks.presentation.ConnectivityStatus
import com.tcp.smarttasks.tasks.presentation.NoInternetScreen
import com.tcp.smarttasks.tasks.presentation.connectivityState
import com.tcp.smarttasks.tasks.presentation.task_detail.TaskDetailAction
import com.tcp.smarttasks.tasks.presentation.task_detail.TaskDetailState
import com.tcp.smarttasks.tasks.presentation.task_detail.TaskDetailViewModel
import com.tcp.smarttasks.tasks.presentation.task_detail.components.CommentTextArea
import com.tcp.smarttasks.tasks.presentation.task_detail.components.TaskDetailScreen
import com.tcp.smarttasks.tasks.presentation.tasks_list.TasksListAction
import com.tcp.smarttasks.tasks.presentation.tasks_list.TasksListEvent
import com.tcp.smarttasks.tasks.presentation.tasks_list.TasksListScreen
import com.tcp.smarttasks.tasks.presentation.tasks_list.TasksViewModel
import com.tcp.smarttasks.tasks.presentation.tasks_list.components.IntroScreen
import com.tcp.smarttasks.tasks.presentation.tasks_list.models.TaskUi
import com.tcp.smarttasks.tasks.presentation.tasks_list.models.formatDueDate
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel = koinViewModel(),
    taskDetailViewModel: TaskDetailViewModel = koinViewModel()
) {
    val state by tasksViewModel.state.collectAsStateWithLifecycle()
    val taskResolvedState by taskDetailViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val connection by connectivityState()
    ObserveAsEvents(events = tasksViewModel.events) { event ->
        when (event) {
            is TasksListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = IntroScreen,
    ) {
        composable<IntroScreen> {
            IntroScreen(
                modifier = modifier.fillMaxWidth()
            )
            LaunchedEffect(Unit) {
                delay(2000L)
                if (connection === ConnectivityStatus.Available) {
                    navController.navigate(TasksListScreen) {
                        navController.popBackStack()
                    }
                } else {
                    navController.navigate(NoInternetScreen) {
                        navController.popBackStack()
                    }
                }
            }
        }
        composable<NoInternetScreen> {
            NoInternetScreen()
        }
        composable<TasksListScreen> {
            val targetDate by tasksViewModel.targetDate.collectAsStateWithLifecycle()
            var onClickExecuted by remember { mutableStateOf(false) }
            val taskStatusIcon = it.savedStateHandle.get<Int>("task_status_icon")
            val taskId = it.savedStateHandle.get<String>("task_id")
            val resolved = it.savedStateHandle.get<Boolean>("resolved")
            val unresolved = it.savedStateHandle.get<Boolean>("unresolved")
            val cantResolve = it.savedStateHandle.get<Boolean>("cantResolve")
            tasksViewModel.updateTask(taskStatusIcon, taskId, unresolved, resolved, cantResolve)
            TasksListScreen(state = state,
                onAction = { action ->
                    if (!onClickExecuted) {
                        tasksViewModel.onAction(action)
                        when (action) {
                            is TasksListAction.OnTaskClick -> {
                                taskResolvedState.taskUi?.let {
                                    if (action.taskUi.id != taskResolvedState.taskUi?.id) {
                                        taskDetailViewModel.resetState(action.taskUi.taskStatusIcon, action.taskUi.unresolved)
                                    }
                                    taskDetailViewModel.setSelectedTask(action.taskUi)
                                }
                                navController.navigate(
                                    TaskDetailScreen(
                                        id = action.taskUi.id,
                                        title = action.taskUi.title,
                                        description = action.taskUi.description,
                                        targetDate = action.taskUi.targetDate.toString(),
                                        dueDate = formatDueDate(action.taskUi.dueDate?.let {
                                            action.taskUi.dueDate
                                        }) ?: "",
                                        priority = action.taskUi.priority
                                    )
                                )
                            }
                        }
                        onClickExecuted = true
                    }
                },
                targetDate = targetDate,
                onForwardClick = { tasksViewModel.incrementTargetDate() },
                onBackClick = { tasksViewModel.decrementTargetDate() })
        }
        composable<TaskDetailScreen> {
            val text = it.savedStateHandle.get<String>("comment")
            val args = it.toRoute<TaskDetailScreen>()
            BackHandler {
                setBackHandlerData(navController, taskResolvedState)
            }
            TaskDetailScreen(
                state = taskResolvedState,
                taskUi = TaskUi(
                    id = args.id,
                    title = args.title,
                    description = args.description,
                    targetDate = LocalDate.parse(args.targetDate),
                    dueDate = LocalDate.parse(
                        args.dueDate,
                        DateTimeFormatter.ofPattern("MMM dd yyyy")
                    ),
                    priority = args.priority,
                    commentSent = text?.let {
                        text
                    },
                    taskStatusIcon = if (taskResolvedState.resolved) R.drawable.sign_resolved
                    else if (taskResolvedState.cantResolve) R.drawable.unresolved_sign
                    else 0,
                ),
                onBackClick = {
                    setBackHandlerData(navController, taskResolvedState)
                },
                onResolveClick = { action ->
                    when (action) {
                        is TaskDetailAction.OnResolvedClick -> {
                            taskDetailViewModel.onAction(action)
                        }

                        is TaskDetailAction.OnCantResolveClick -> {
                            taskDetailViewModel.onAction(action)
                        }

                        is TaskDetailAction.OnAddComment -> {
                            taskDetailViewModel.onAction(action)
                            navController.navigate(CommentScreen)
                        }
                    }
                }
            )
        }
        composable<CommentScreen> {
            CommentTextArea(onClick = {
                navController.popBackStack()
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "comment",
                    "Comment sent to manager"
                )
            })
        }
    }
}

private fun setBackHandlerData(
    navController: NavHostController,
    taskResolvedState: TaskDetailState
) {
    navController.popBackStack()
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "task_status_icon",
        if (taskResolvedState.resolved) R.drawable.sign_resolved
        else if (taskResolvedState.cantResolve) R.drawable.unresolved_sign
        else 0
    )
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "task_id",
        taskResolvedState.taskUi?.id
    )
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "resolved",
        taskResolvedState.resolved
    )
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "cantResolve",
        taskResolvedState.cantResolve
    )
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "unresolved",
        taskResolvedState.unresolved
    )
}

@Serializable
object CommentScreen

@Serializable
object IntroScreen

@Serializable
object TasksListScreen

@Serializable
object NoInternetScreen

@Serializable
data class TaskDetailScreen(
    val id: String,
    val title: String,
    val description: String,
    val dueDate: String,
    val targetDate: String,
    val priority: Int
)