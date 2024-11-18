package com.tcp.smarttasks.tasks.presentation

sealed class ConnectivityStatus {
    data object Available: ConnectivityStatus()
    data object Unavailable: ConnectivityStatus()
}