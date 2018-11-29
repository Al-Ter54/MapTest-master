package com.example.shifthackz.maptest.repository.manger

import android.app.Application
import android.content.Context
import com.example.shifthackz.maptest.repository.database.MapTestDatabase
import com.example.shifthackz.maptest.repository.database.dao.MapTestDAO
import com.example.shifthackz.maptest.repository.database.entity.MapTestEntity
import com.example.shifthackz.maptest.repository.server.ApiService
import com.example.shifthackz.maptest.repository.server.ServerCommunicator
import io.reactivex.Single
import org.koin.dsl.module.applicationContext


class MapTestManager {
    class ExtApiService():ApiService {
        override fun getAll(): Single<List<MapTestEntity>> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
    val mExtApiService:ApiService = ExtApiService()
    val mServerCommunicator:ServerCommunicator = ServerCommunicator(mExtApiService)

    fun getMapItems(context: Context): Single<ArrayList<MapTestEntity>> {
        return mServerCommunicator.getAll(context)
    }
    fun setMapItems(singleResponse: Single<ArrayList<MapTestEntity>>){

        val database = MapTestDatabase.getInstance(context = this@RoomApplication)
        database.mapTestDao().addAll(singleResponse)
    }
}