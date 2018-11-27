package com.example.shifthackz.maptest.domain

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.annotation.StringRes
import com.example.shifthackz.maptest.App

//абстрактный базовый класс ViewModel
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    //метод-геттер для контекста
    //швыряет контекст аппликейшна
    fun getContext() = getApplication<App>()

    //метод-геттер стринги по ресАйдишнику
    fun getString(@StringRes id: Int): String = getContext().getString(id)
}