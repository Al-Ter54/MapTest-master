package com.example.shifthackz.maptest.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.example.shifthackz.maptest.repository.database.MapTestDatabase
import com.example.shifthackz.maptest.repository.database.entity.MapTestEntity
import com.example.shifthackz.maptest.repository.server.ServerCommunicator

//класс репозитория
//это походу менеджер, ибо работает с дао и коммуникатором
class MapTestRepository(private val serverCommunicator: ServerCommunicator,
                        private val mainDatabase: MapTestDatabase) {

    //реализация геттера из апи интерфейса
    fun getAll(context: Context): LiveData<List<MapTestEntity>> {
        //подписываемся отсюда в поток коммуникатора
        serverCommunicator.getAll(context)
            .subscribe({ it -> mainDatabase.mainDao().updateAll(it) }, { it -> it.printStackTrace() })
        return mainDatabase.mainDao().getAll()
    }

}
