package com.example.raiffeisentest.view_models

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.raiffeisentest.models.User
import com.example.raiffeisentest.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "UserViewModel"
class UserViewModel(application: Application, private val repository: UserRepository) : AndroidViewModel(application) {
    val isLoading = ObservableBoolean()

    val users: MutableLiveData<ArrayList<User>> by lazy {
        MutableLiveData<ArrayList<User>>().also {
            getUsers(0)
        }
    }

    fun getUsers(page: Int) {
        Log.v(TAG, "getUsers")
        isLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getUsers(page)
            withContext(Dispatchers.Main) {
                users.value = result
                isLoading.set(false)
            }
        }
    }
}