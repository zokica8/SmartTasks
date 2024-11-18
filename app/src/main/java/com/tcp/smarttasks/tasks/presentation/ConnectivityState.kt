package com.tcp.smarttasks.tasks.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import com.tcp.smarttasks.core.data.currentConnectivityState
import com.tcp.smarttasks.core.data.observeConnectivity

@Composable
fun connectivityState() : State<ConnectivityStatus> {
    val context = LocalContext.current

    return produceState(initialValue = context.currentConnectivityState) {
        context.observeConnectivity().collect { value = it }
    }
}