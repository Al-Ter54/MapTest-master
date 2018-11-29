package com.example.shifthackz.maptest.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.shifthackz.maptest.repository.database.dao.MapTestDAO
import com.example.shifthackz.maptest.repository.database.entity.MapTestEntity

//этот класс походу сетит дао для нашей сущности
@Database(entities = [(MapTestEntity::class)], version = 1)
abstract class MapTestDatabase : RoomDatabase() {

    abstract fun mapTestDao(): MapTestDAO

    companion object {

        /**
         * The only instance
         */
        private var sInstance: MapTestDatabase? = null

        /**
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.
         */
        @Synchronized
        fun getInstance(context: Context): MapTestDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, MapTestDatabase::class.java, "example")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }
    }

}