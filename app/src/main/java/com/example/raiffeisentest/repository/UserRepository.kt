package com.example.raiffeisentest.repository

import com.example.raiffeisentest.UserListScreenState
import com.example.raiffeisentest.interfaces.RetrofitAPI
import com.example.raiffeisentest.interfaces.UserDao
import com.example.raiffeisentest.models.User
import com.example.raiffeisentest.models.UsersModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


internal class UserRepository(
    private val retrofitService: RetrofitAPI,
    private val userDao: UserDao
) : KoinComponent {

    suspend fun getUsersScreenState(page: Int): UserListScreenState {
        return try {
            var users = retrofitService.getUsers(page, 20, "abc")
            if (users.results.size == 0) {
                users = UsersModel(getUsersFromDB(), get())
            } else {
                addUsers(users.results)
            }
            return UserListScreenState.Success(
                users = users,
                lastCalledPage = page
            )
        } catch (e: Exception) {
            UserListScreenState.Error
        }
    }

    private suspend fun getUsersFromDB(): ArrayList<User> {
        return ArrayList(userDao.getUsers())
    }

    private suspend fun addUsers(users: List<User>) {
        userDao.addUser(users)
    }
}