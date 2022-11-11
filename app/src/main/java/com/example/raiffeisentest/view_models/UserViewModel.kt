package com.example.raiffeisentest.view_models

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.raiffeisentest.models.UsersModel
import com.example.raiffeisentest.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "UserViewModel"
class UserViewModel(application: Application, private val repository: UserRepository) : AndroidViewModel(application) {
    val isLoading = ObservableBoolean()
    val lastCalledPage = ObservableInt()

    val users: MutableLiveData<UsersModel> by lazy {
        MutableLiveData<UsersModel>().also {
            getUsers(1)
        }
    }

    fun getUsers(page: Int) {
        Log.v(TAG, "getUsers")
        isLoading.set(true)
        lastCalledPage.set(page)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getUsers(page)
            withContext(Dispatchers.Main) {
                users.value = result
                isLoading.set(false)
            }
        }
    }
}