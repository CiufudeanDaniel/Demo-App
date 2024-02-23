package com.example.raiffeisentest.view_models

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.raiffeisentest.UserListScreenState
import com.example.raiffeisentest.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

private const val TAG = "UserViewModel"

internal class UserViewModel(
    application: Application,
    private val repository: UserRepository
) : AndroidViewModel(application), KoinComponent {

    val lastCalledPage = ObservableInt()

    private val _inputScreenState =
        MutableLiveData<UserListScreenState>(UserListScreenState.Loading)
    val inputScreenState : LiveData<UserListScreenState>
        get() = _inputScreenState

    init {
        getUsersScreenState(1)
    }

    fun getUsersScreenState(page: Int) {
        Log.v(TAG, "getUsers")
        lastCalledPage.set(page)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getUsersScreenState(page)
            withContext(Dispatchers.Main) {
                _inputScreenState.value = result
            }
        }
    }
}