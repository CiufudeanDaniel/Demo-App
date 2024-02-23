package com.example.raiffeisentest

import com.example.raiffeisentest.models.UsersModel

internal sealed class UserListScreenState {
    object Loading : UserListScreenState()
    object Error : UserListScreenState()
    data class Success(
        val users: UsersModel,
        val lastCalledPage: Int,
    ) : UserListScreenState()
}