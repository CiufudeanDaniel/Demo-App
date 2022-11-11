package com.example.raiffeisentest.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoModel(
    val seed: String,
    val results: Int,
    val page: Int
) : Parcelable
