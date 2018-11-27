package com.example.shifthackz.maptest.domain

import android.app.Application
import android.arch.lifecycle.LiveData
import com.example.shifthackz.maptest.repository.MapTestRepository
import com.example.shifthackz.maptest.repository.database.entity.MapTestEntity

//наш кастом вью модел, наследуется от базовой вью модели
class MapTestViewModel(application: Application, private val mainRepository: MapTestRepository) : BaseViewModel(application) {
    //геттер всех объектов
    //тип LiveData, работающая со списком обскур-сущностей
    //швыряем все и вся с главного репозитория
    fun getAll(): LiveData<List<MapTestEntity>> = mainRepository.getAll()

    //метод добавления новой сущности
    fun addNew(mainEntity: MapTestEntity) = mainRepository.addNew(mainEntity)

}