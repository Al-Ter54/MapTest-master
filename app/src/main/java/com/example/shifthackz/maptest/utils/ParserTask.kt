package com.example.shifthackz.maptest.utils

import android.graphics.Color
import android.os.AsyncTask
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONObject
import com.google.android.gms.maps.GoogleMap
import java.util.*

class ParserTask(var mMap: GoogleMap) : AsyncTask<String, Integer, List<List<HashMap<String, String>>>>() {

    private val routeReadyListener = IRouteReadyImpl()

    // Parsing the data in non-ui thread
    override fun doInBackground(vararg jsonData: String): List<List<HashMap<String, String>>>? {

        val jObject: JSONObject
        var routes: List<List<HashMap<String, String>>>? = null

        try {
            jObject = JSONObject(jsonData[0])
            val parser = DirectionsJSONParser()

            routes = parser.parse(jObject)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return routes
    }

    override fun onPostExecute(result: List<List<HashMap<String, String>>>) {
        var points: ArrayList<LatLng>? = null
        var lineOptions: PolylineOptions? = null
        val markerOptions = MarkerOptions()

        for (i in result.indices) {
            points = ArrayList()
            lineOptions = PolylineOptions()

            val path = result[i]

            for (j in path.indices) {
                val point = path[j]

                val lat = java.lang.Double.parseDouble(point["lat"])
                val lng = java.lang.Double.parseDouble(point["lng"])
                val position = LatLng(lat, lng)

                points!!.add(position)
            }

            lineOptions.addAll(points!!)
            lineOptions.width(14f)
            lineOptions.color(Color.rgb(237, 183, 21))
            lineOptions.geodesic(true)
            val dashedPattern = Arrays.asList(Dot(), Gap(10f))
            lineOptions.pattern(dashedPattern)

            routeReadyListener.showRoute(mMap, points!!)

        }

        // Drawing polyline in the Google Map for the i-th route
        if (lineOptions != null) {

            mMap.addPolyline(lineOptions)
        }
    }
}