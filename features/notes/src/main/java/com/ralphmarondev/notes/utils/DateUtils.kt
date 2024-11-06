package com.ralphmarondev.notes.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun getCurrentDate(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")
    val formattedDate = currentDate.format(formatter)

    return formattedDate
}

fun getCurrentTime(): String {
    val currentTime = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
    val formattedTime = currentTime.format(formatter)

    return formattedTime
}

fun getWeekday(savedDate: String): String {
    val formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")
    val parsedDate = LocalDate.parse(savedDate, formatter)

    val weekDayFormatter = DateTimeFormatter.ofPattern("EEE")
    val weekday = parsedDate.format(weekDayFormatter).uppercase()

    return weekday
}

fun getDayOfMonth(savedDate: String): String {
    val formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")
    val parsedDate = LocalDate.parse(savedDate, formatter)

    val dayOfMonth = parsedDate.dayOfMonth.toString()
    return dayOfMonth
}

fun getTimeInMilitary(savedTime: String): String {
    val amPmFormatter = DateTimeFormatter.ofPattern("h:mm a")
    val parsedTime = LocalTime.parse(savedTime, amPmFormatter)

    val militaryFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val militaryTime = parsedTime.format(militaryFormatter)

    return militaryTime
}