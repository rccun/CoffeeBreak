package org.coffeebreak.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class DatetimeUseCase {
    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDate(date: Instant): Pair<String, String> {
        val instant = Instant.parse(date.toString())

        val localDate = instant
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date

        val formatter = DateTimeFormatter
            .ofPattern("d MMMM", Locale("ru"))

        return formatter.format(
            java.time.LocalDate.of(
                localDate.year,
                localDate.monthNumber,
                localDate.dayOfMonth
            )
        ) to
        "%02d:%02d".format(
            date.toLocalDateTime(
                TimeZone.currentSystemDefault()
            ).hour, date.toLocalDateTime(
                TimeZone.currentSystemDefault()
            ).minute
        )




//        val day = date.toLocalDateTime(
//            TimeZone.currentSystemDefault()
//        ).date.toString()

//        val instant = Instant.parse(date.toString())
//
//        val formatter = DateTimeFormatter
//            .ofPattern("d MMMM", Locale("ru"))
//            .withZone(ZoneId.systemDefault())
//
//
//        val day2 = formatter.format(instant)
//        val time = "%02d:%02d".format(
//            date.toLocalDateTime(
//                TimeZone.currentSystemDefault()
//            ).hour, date.toLocalDateTime(
//                TimeZone.currentSystemDefault()
//            ).minute
//        )
//        return day2 to time
    }

}