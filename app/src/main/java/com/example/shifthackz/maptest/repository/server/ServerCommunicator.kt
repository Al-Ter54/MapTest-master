package com.example.shifthackz.maptest.repository.server

import android.content.Context
import com.example.shifthackz.maptest.R
import com.example.shifthackz.maptest.repository.database.entity.MapTestEntity
import com.example.shifthackz.maptest.utils.MokUtils
import com.example.shifthackz.maptest.utils.TestMap
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

//класс-коммуникатор
//в конструкторе создаем объект класса интерфейса апи сервиса
class ServerCommunicator(private val apiService: ApiService) {

    //создание объекта с константами внутри класса
    companion object {
        //дефолтный таймаут
        private const val DEFAULT_TIMEOUT = 10 // seconds
        //дефолтное число последующих попыток
        private const val DEFAULT_RETRY_ATTEMPTS: Long = 4
    }

    //обсервабл трансформер
    private fun <T> observableTransformer(): ObservableTransformer<T, T> = ObservableTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }


    //сингл трансформер
    private fun <T> singleTransformer(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }

//    fun getUser(): Single<User> {
//        DLog.d("Merov", "ServerCommunicator getUser: ")
//        val user = User()
//        user.setId(1)
//        user.setName("TEST NAME")
//        return Single.just<User>(user).compose(singleTransformer<Any>())
//        //        return mApiService.getUser().compose(singleTransformer());
//    }

    //реализация геттера из апи сервиса
    fun getAll(context:Context): Single<ArrayList<MapTestEntity>> {
        val titles: Array<String> = context.resources.getStringArray(R.array.mok_title)
        val descriptions: Array<String> = context.resources.getStringArray(R.array.mok_description)
        val phones: Array<String> = context.resources.getStringArray(R.array.mok_phone)
        val ids: Array<String> = context.resources.getStringArray(R.array.mok_id)
        val latitudes: Array<String> = context.resources.getStringArray(R.array.mok_latitude)
        val longitudes: Array<String> = context.resources.getStringArray(R.array.mok_longitude)

        val mokResult: ArrayList<MapTestEntity> ?= null
        for(i in 0..(ids.size-1)){
            mokResult!!.add(MapTestEntity(ids[i].toInt(), titles[i], descriptions[i], phones[i], latitudes[i].toDouble(), longitudes[i].toDouble()))
        }
        return Single.just(mokResult)
    }
}