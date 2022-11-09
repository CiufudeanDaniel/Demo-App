package com.example.raiffeisentest.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NameModel(
    val title: String,
    val first: String,
    val last: String
) : Parcelable
