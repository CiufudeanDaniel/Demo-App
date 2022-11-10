package com.example.raiffeisentest.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pictures(
    val large: String,
    val medium: String,
    val thumbnail: String
) : Parcelable
