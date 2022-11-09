package com.example.raiffeisentest.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val gender: String,
    val name: NameModel
) : Parcelable

@Parcelize
data class ResultModel(
    val results: ArrayList<User>
) : Parcelable