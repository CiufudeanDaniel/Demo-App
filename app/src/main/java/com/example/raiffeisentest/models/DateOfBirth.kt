package com.example.raiffeisentest.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class DateOfBirth(
    val date: String,
    val age: Int
) : Parcelable {
    fun getTime() : String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).parse(date)
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = dateFormat!!
        var hours = ""
        var minutes = ""
        if (calendar.get(Calendar.HOUR_OF_DAY) < 10)
            hours += "0"
        if (calendar.get(Calendar.MINUTE) < 10)
            minutes += "0"

        hours += calendar.get(Calendar.HOUR_OF_DAY)
        minutes += calendar.get(Calendar.MINUTE)
        return "$hours:$minutes"
    }
}
