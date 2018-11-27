package com.example.shifthackz.maptest.koin

import com.example.shifthackz.maptest.domain.MapTestViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

//модуль вью-модели с геттерами внутри
val viewModelModule: Module = applicationContext {
    viewModel { MapTestViewModel(get(), get()) }
}