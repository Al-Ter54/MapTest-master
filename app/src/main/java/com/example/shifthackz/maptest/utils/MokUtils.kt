package com.example.shifthackz.maptest.utils

import android.content.Context
import com.example.shifthackz.maptest.R

class MokUtils {
    fun getMapMok(context:Context) : ArrayList<TestMap>{
        val titles: Array<String> = context.resources.getStringArray(R.array.mok_title)
        val descriptions: Array<String> = context.resources.getStringArray(R.array.mok_description)
        val phones: Array<String> = context.resources.getStringArray(R.array.mok_phone)
        val ids: Array<String> = context.resources.getStringArray(R.array.mok_id)
        val latitudes: Array<String> = context.resources.getStringArray(R.array.mok_latitude)
        val longitudes: Array<String> = context.resources.getStringArray(R.array.mok_longitude)

        val mokResult: ArrayList<TestMap> ?= null
        for(i in 0..(ids.size-1)){
            mokResult!!.add(TestMap(titles[i], phones[i], ids[i].toInt(), latitudes[i].toDouble(), longitudes[i].toDouble(), descriptions[i]))
        }
        return mokResult!!
    }
}