package com.example.raiffeisentest.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.raiffeisentest.repository.UserRepository

internal class MyViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {

//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(UserViewModel1::class.java)) {
//            return UserViewModel1(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//        return super.create(modelClass)
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel1::class.java)) {
            return UserViewModel1(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}