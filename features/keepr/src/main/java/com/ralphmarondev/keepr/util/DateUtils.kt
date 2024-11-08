package com.ralphmarondev.keepr.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentDateInString(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}