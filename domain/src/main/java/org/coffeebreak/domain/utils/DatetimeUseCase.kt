package org.coffeebreak.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.ExperimentalTime

class DatetimeUseCase {
    @OptIn(ExperimentalTime::class)
    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDate(date: String): Pair<String, String> {
        val isoString = "2026-02-11T08:34:43.263248+00:00"

        val dateTime = ZonedDateTime.parse(date)
            .withZoneSameInstant(ZoneId.systemDefault())

        val dateFormatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
        val date = dateTime.format(dateFormatter)

        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val time = dateTime.format(timeFormatter)


        return Pair(date, time)

//        val instant = Instant.parse(date.toString())
//
//        val localDate = instant
//            .toLocalDateTime(TimeZone.currentSystemDefault())
//            .date
//
//        val formatter = DateTimeFormatter
//            .ofPattern("d MMMM", Locale("ru"))
//
//        return formatter.format(
//            java.time.LocalDate.of(
//                localDate.year,
//                localDate.monthNumber,
//                localDate.dayOfMonth
//            )
//        ) to
//        "%02d:%02d".format(
//            date.toLocalDateTime(
//                TimeZone.currentSystemDefault()
//            ).hour, date.toLocalDateTime(
//                TimeZone.currentSystemDefault()
//            ).minute
//        )
    }
}