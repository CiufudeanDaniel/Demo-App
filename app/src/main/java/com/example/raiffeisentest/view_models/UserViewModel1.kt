package com.example.raiffeisentest.view_models

import android.util.Log
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raiffeisentest.UserListScreenState
import com.example.raiffeisentest.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "UserViewModel1"

internal class UserViewModel1(
    private val repository: UserRepository
) : ViewModel() {
    val lastCalledPage = ObservableInt()

    private val _uiState: MutableStateFlow<UserListScreenState> =
        MutableStateFlow(UserListScreenState.Loading)
    val uiState: StateFlow<UserListScreenState> = _uiState.asStateFlow()

    init {
        getUsersScreenState(1)
    }

    fun getUsersScreenState(page: Int) {
        Log.v(TAG, "getUsers")
        lastCalledPage.set(page)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getUsersScreenState(page)
            withContext(Dispatchers.Main) {
                _uiState.emit(result)
            }
        }
    }
}