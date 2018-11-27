package com.example.shifthackz.maptest.utils

import android.content.Context
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class OwnIconRendered(context: Context, map: GoogleMap, clusterManager: ClusterManager<TestMap>) : DefaultClusterRenderer<TestMap>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(item: TestMap, markerOptions: MarkerOptions) {
        markerOptions.icon(item.mIcon)
        markerOptions.snippet(item.snippet)
        markerOptions.title(item.title)
        markerOptions.infoWindowAnchor(3.4f, 0.9f)// x y 2.4f, 0.8f
        super.onBeforeClusterItemRendered(item, markerOptions)
    }
}