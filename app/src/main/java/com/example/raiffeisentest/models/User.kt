package com.example.raiffeisentest.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    val gender: String,
    @Embedded
    val name: NameModel,
    @Embedded
    val picture: Pictures,
    val nat: String,
    @Embedded
    val dob: DateOfBirth,
    @Embedded
    @PrimaryKey
    val login: LoginModel
) : Parcelable

@Parcelize
data class UsersModel(
    val results: ArrayList<User>,
    val info: InfoModel
) : Parcelable