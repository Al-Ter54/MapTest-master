package com.example.shifthackz.maptest.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.shifthackz.maptest.repository.database.dao.MapTestDAO
import com.example.shifthackz.maptest.repository.database.entity.MapTestEntity

//этот класс походу сетит дао для нашей сущности
@Database(entities = [(MapTestEntity::class)], version = 1)
abstract class MapTestDatabase : RoomDatabase() {

    abstract fun mainDao(): MapTestDAO

}