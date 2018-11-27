package com.example.shifthackz.maptest.repository.server

import com.example.shifthackz.maptest.repository.database.entity.MapTestEntity
import io.reactivex.Single
import retrofit2.http.GET

//интерфейс апи сервиса
interface ApiService {
    //геттер всего и вся
    @GET("/all")
    fun getAll(): Single<List<MapTestEntity>>

}