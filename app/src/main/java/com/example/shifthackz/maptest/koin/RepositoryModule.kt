package com.example.shifthackz.maptest.koin

import android.arch.persistence.room.Room
import android.content.Context
import com.example.shifthackz.maptest.repository.MapTestRepository
import com.example.shifthackz.maptest.repository.database.MapTestDatabase
import com.example.shifthackz.maptest.repository.server.ApiService
import com.example.shifthackz.maptest.repository.server.ServerCommunicator
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//модуль репозитория
val repositoryModule: Module = applicationContext {
    //зависимость от контекста
    provide { provideDatabase(androidApplication().applicationContext) }
    //зависимость от коммуникатора
    provide { provideServerCommunicator() }
    //зависимость от расшаренных настроек
    provide { provideSharedPrefs(androidApplication().applicationContext) }
    //зависимость от обскуры репозитория
    provide { MapTestRepository(get(), get()) }
}

//метод провайдящий зависимость от БД
private fun provideDatabase(context: Context) = Room.databaseBuilder(context, MapTestDatabase::class.java, "scaffold-database").build()
//метод провайдящий расшаренные настройки
private fun provideSharedPrefs(context: Context) = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

//константа урла апи сервера
private const val API_URL = "http://jsonstub.com/"

//метод провайдящий сервер коммуникатор
private fun provideServerCommunicator(): ServerCommunicator {
    //билдер ок хттп
    val okHttpClientBuilder = OkHttpClient.Builder()
        .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)

//        if (BuildConfig.DEBUG) {
//            val httpLoggingInterceptor = HttpLoggingInterceptor()
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
//                    .addNetworkInterceptor(StethoInterceptor())
//        }

    //билдер ретрофита
    val retrofitBuilder = Retrofit.Builder()
        .client(okHttpClientBuilder.build())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    //билдим ретрофит
    val retrofit = retrofitBuilder.baseUrl(API_URL).build()
    //билдим апи сервис
    val apiService = retrofit.create<ApiService>(ApiService::class.java)
    //швыряем собранный коммуникатор
    return ServerCommunicator(apiService)
}