package com.example.raiffeisentest.repository

import com.example.raiffeisentest.interfaces.RetrofitAPI
import com.example.raiffeisentest.models.UsersModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


class UserRepository(private val retrofitService: RetrofitAPI) : KoinComponent {

    suspend fun getUsers(page: Int) : UsersModel {
        return try {
            retrofitService.getUsers(page, 20, "abc")
        } catch (e: Exception) {
            get()
        }
    }
}