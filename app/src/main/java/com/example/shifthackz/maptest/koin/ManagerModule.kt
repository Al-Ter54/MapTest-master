package com.example.shifthackz.maptest.koin

import com.example.shifthackz.maptest.repository.MapTestRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val managerModule: Module = applicationContext {
    //зависимость от контекста
    provide { provideDatabase(androidApplication().applicationContext) }
    //зависимость от коммуникатора
    provide { provideServerCommunicator() }
    //зависимость от расшаренных настроек
    provide { provideSharedPrefs(androidApplication().applicationContext) }
    //зависимость от обскуры репозитория
    provide { MapTestRepository(get(), get()) }
}