package com.example.shifthackz.maptest.utils

import com.example.shifthackz.maptest.MapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.GoogleMap


class IRouteReadyImpl : MapFragment(), IRouteReady {
    override fun showRoute(mMap: GoogleMap, routePoints: List<LatLng>) {
        //super.setAnimation(mMap, routePoints)
    }
}