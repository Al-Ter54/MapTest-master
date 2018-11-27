package com.example.shifthackz.maptest.utils

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.GoogleMap


interface IRouteReady {
    fun showRoute(mMap: GoogleMap, routePoints: List<LatLng>)
}