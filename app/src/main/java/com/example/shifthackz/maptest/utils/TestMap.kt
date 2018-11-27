package com.example.shifthackz.maptest.utils

import android.graphics.drawable.Icon
import com.example.shifthackz.maptest.R
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class TestMap(
    var mTitle : String,
    var mPhone : String,
    var mId: Int,
    var mLatitude: Double,
    var mLongitude: Double,
    var mDiscribe: String,
    var mIcon:BitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_foreground)
) : ClusterItem {
    override fun getSnippet(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTitle(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPosition(): LatLng {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}