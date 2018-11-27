package com.example.shifthackz.maptest.repository.database.dao


import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.example.shifthackz.maptest.repository.database.entity.MapTestEntity

//константа с именем таблицы
const val TABLE_NAME: String = "mok"

//ДАО для нашей структура данных
//в виде интерфейса
@Dao
interface MapTestDAO {
    //геттер всего и вся
    @Query("select * from $TABLE_NAME")
    fun getAll(): LiveData<List<MapTestEntity>>
    //добавление всего и вся, заменяет при конфликте
    @Insert(onConflict = REPLACE)
    fun addAll(all: List<MapTestEntity>)
    //метод апдейтит все
    @Update(onConflict = REPLACE)
    fun updateAll(all: List<MapTestEntity>)
    //удаляет все
    @Delete
    fun deleteAll(all: List<MapTestEntity>)

    //геттер по айдишнику
    @Query("select * from $TABLE_NAME where id = :id")
    fun getById(id: Int): LiveData<MapTestEntity>
    //апдейт одного элемента
    @Update(onConflict = REPLACE)
    fun update(mainEntity: MapTestEntity)
    //добавления одного элемента
    @Insert(onConflict = REPLACE)
    fun add(mainEntity: MapTestEntity)
    //удаление одного элемента
    @Delete
    fun delete(mainEntity: MapTestEntity)

}