package com.tcp.smarttasks.core.presentation.util

import android.content.Context
import com.tcp.smarttasks.R
import com.tcp.smarttasks.core.domain.util.NetworkError

fun NetworkError.toString(context: Context) : String {
    val resId = when(this) {
        NetworkError.NO_INTERNET -> R.string.no_internet
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.unknown_error
        NetworkError.SERVER_ERROR -> R.string.server_error
        NetworkError.CLIENT_ERROR -> R.string.client_error
    }
    return context.getString(resId)
}