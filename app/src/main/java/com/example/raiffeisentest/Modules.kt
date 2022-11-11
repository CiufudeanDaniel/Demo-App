package com.example.raiffeisentest

import com.example.raiffeisentest.interfaces.RetrofitAPI
import com.example.raiffeisentest.models.InfoModel
import com.example.raiffeisentest.models.UsersModel
import com.example.raiffeisentest.repository.UserRepository
import com.example.raiffeisentest.service.RetrofitClientInstance.Companion.getRetrofitInstance
import com.example.raiffeisentest.view_models.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::UserViewModel)
}

val apiModule = module {
    single { getRetrofitInstance().create(RetrofitAPI::class.java) }
}

val repositoryModule = module {
    singleOf(::UserRepository)
}

val modelModule = module {
    factory {
        InfoModel("", 0, 0)
    }
    factory {
        UsersModel(arrayListOf(), get())
    }
}

val parentModule = module {
    includes(apiModule, repositoryModule, viewModelModule, modelModule)
}