package com.example.raiffeisentest

import com.example.raiffeisentest.interfaces.RetrofitAPI
import com.example.raiffeisentest.repository.UserRepository
import com.example.raiffeisentest.service.RetrofitClientInstance.Companion.getRetrofitInstance
import com.example.raiffeisentest.view_models.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val viewmodelModule = module {
    viewModelOf(::UserViewModel)
}

val apiModule = module {
    single { getRetrofitInstance().create(RetrofitAPI::class.java) }
}

val repositoryModule = module {
    singleOf(::UserRepository)
}

val parentModule = module {
    includes(apiModule, repositoryModule, viewmodelModule)
}