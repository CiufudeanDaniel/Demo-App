package com.example.raiffeisentest.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class DateOfBirth(
    val date: Date,
    val age: Int
) : Parcelable {
    fun getTime() : String {
        var hours = ""
        var minutes = ""
        if (date.hours < 10)
            hours += "0"
        if (date.minutes < 10)
            minutes += "0"

        hours += date.hours
        minutes += date.minutes
        return "$hours:$minutes"
    }
}
