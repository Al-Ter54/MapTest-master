package com.example.shifthackz.maptest.repository.server

import android.content.Context
import com.example.shifthackz.maptest.repository.database.entity.MapTestEntity
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
    fun getAll(): Single<List<MapTestEntity>> = apiService.getAll().compose(singleTransformer())

    fun getMapItems(context: Context): Single<List<MapTestEntity>> {
        return Single.just(emptyList())
    }
}