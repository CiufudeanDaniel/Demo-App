package com.example.raiffeisentest

import com.example.raiffeisentest.interfaces.RetrofitAPI
import com.example.raiffeisentest.models.InfoModel
import com.example.raiffeisentest.models.UsersModel
import com.example.raiffeisentest.repository.UserRepository
import com.example.raiffeisentest.service.AppDatabase
import com.example.raiffeisentest.service.RetrofitClientInstance.Companion.getRetrofitInstance
import com.example.raiffeisentest.view_models.UserViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::UserViewModel)
}

val apiModule = module {
    single { getRetrofitInstance().create(RetrofitAPI::class.java) }
}

val daoModule = module {
    single {
        AppDatabase.getDatabase(androidApplication()).userDao()
    }
}

val repositoryModule = module {
    singleOf(::UserRepository)
}

val modelModule = module {
    factory {
        InfoModel("abc", 20, 1)
    }
    factory {
        UsersModel(arrayListOf(), get())
    }
}

val parentModule = module {
    includes(apiModule, daoModule, repositoryModule, viewModelModule, modelModule)
}