package com.tcp.smarttasks.core.domain.util

enum class NetworkError: Error {
    NO_INTERNET,
    CLIENT_ERROR,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN
}