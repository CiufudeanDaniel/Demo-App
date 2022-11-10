package com.example.raiffeisentest.repository

import com.example.raiffeisentest.interfaces.RetrofitAPI
import com.example.raiffeisentest.models.User

class UserRepository(private val retrofitService: RetrofitAPI) {

    suspend fun getUsers(page: Int) : ArrayList<User> {
        return try {
            retrofitService.getUsers(page, 20, "abc").results
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}