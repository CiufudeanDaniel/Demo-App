package com.example.raiffeisentest.models

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel(
    @PrimaryKey
    val uuid: String
) : Parcelable
