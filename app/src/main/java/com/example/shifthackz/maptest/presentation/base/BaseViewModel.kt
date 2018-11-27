package com.example.shifthackz.maptest

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.annotation.StringRes
import com.example.shifthackz.maptest.App

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    fun getContext() = getApplication<App>()
    fun getString(@StringRes id: Int): String = getContext().getString(id)
}