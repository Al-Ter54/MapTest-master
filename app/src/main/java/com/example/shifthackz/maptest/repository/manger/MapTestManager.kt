package com.example.shifthackz.maptest.repository.manger

import android.content.Context
import com.example.shifthackz.maptest.repository.database.entity.MapTestEntity
import com.example.shifthackz.maptest.repository.server.ApiService
import com.example.shifthackz.maptest.repository.server.ServerCommunicator
import io.reactivex.Single


class MapTestManager {
    class ExtApiService():ApiService {
        override fun getAll(): Single<List<MapTestEntity>> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
    val mExtApiService:ApiService = ExtApiService()
    val mServerCommunicator:ServerCommunicator = ServerCommunicator(mExtApiService)

    fun getMapItems(context: Context): Single<List<MapTestEntity>> {
        return mServerCommunicator.getMapItems(context)
    }
}