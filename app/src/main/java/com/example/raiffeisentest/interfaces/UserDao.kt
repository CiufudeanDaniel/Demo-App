package com.example.raiffeisentest.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.raiffeisentest.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(users: List<User>)

    @Query("SELECT * FROM user_table")
    suspend fun getUsers(): List<User>
}