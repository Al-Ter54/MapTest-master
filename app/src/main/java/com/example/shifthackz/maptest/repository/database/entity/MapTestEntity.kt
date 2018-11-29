package com.example.shifthackz.maptest.repository.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.android.gms.maps.model.BitmapDescriptor


@Entity(tableName = "mok")
data class MapTestEntity(@PrimaryKey(autoGenerate = true)
                         var id: Int,
                         var title: String,
                         var description: String,
                         var phone: String,
                         var latitude: Double,
                         var longitude: Double
)