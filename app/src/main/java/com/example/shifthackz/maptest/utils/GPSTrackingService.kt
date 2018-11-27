package com.example.shifthackz.maptest.utils

import android.annotation.SuppressLint
import android.location.LocationListener
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

class GPSTrackingService : Service(), LocationListener {

    private var mContext: Context? = null
    // flag for GPS status
    private var isGPSEnabled : Boolean = false
    // flag for network status
    private var isNetworkEnabled : Boolean = false

    private var canGetLocation : Boolean = false

    private var location: Location? = Location("")
    private var latitude: Double? = null
    private var longitude: Double? = null

    // The minimum distance to change Updates in meters
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10 // 10 meters

    // The minimum time between updates in milliseconds
    private val MIN_TIME_BW_UPDATES = (1000 * 60 * 1).toLong() // 1 minute

    // Declaring a Location Manager
    private var locationManager: LocationManager? = null

    fun GPSTrackingService(context: Context) {
        this.mContext = context
        getLocation()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onLocationChanged(location: Location) {

    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

    @SuppressLint("MissingPermission")
    fun getLocation(): Location? {
        try {
            locationManager = mContext?.getSystemService(LOCATION_SERVICE) as LocationManager

            // getting GPS status
            isGPSEnabled = locationManager!!
                .isProviderEnabled(LocationManager.GPS_PROVIDER)

            // getting network status
            isNetworkEnabled = locationManager!!
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager!!.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                    )
                    Log.d("Network", "Network")
                    if (locationManager != null) {
                        location = locationManager!!
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (location != null) {
                            latitude = location!!.getLatitude()
                            longitude = location!!.getLongitude()
                        }
                    }

                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager!!.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                        )
                        Log.d("GPS Enabled", "GPS Enabled")
                        if (locationManager != null) {
                            location = locationManager!!
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                latitude = location!!.getLatitude()
                                longitude = location!!.getLongitude()
                            }
                        }
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return location
    }

    fun getLatitude(): Double? {
        if (location != null) {
            latitude = location!!.getLatitude()
        }

        // return latitude
        return latitude
    }

    fun getLongitude(): Double? {
        if (location != null) {
            longitude = location!!.getLongitude()
        }

        // return longitude
        return longitude
    }

    fun canGetLocation(): Boolean {
        return this.canGetLocation
    }

    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(mContext)

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings")

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?")

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings") { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext!!.startActivity(intent)
        }

        // on pressing cancel button
        alertDialog.setNegativeButton(
            "Cancel"
        ) { dialog, which -> dialog.cancel() }

        // Showing Alert Message
        alertDialog.show()
    }

    fun stopUsingGPS() {
        if (locationManager != null) {
            locationManager!!.removeUpdates(this@GPSTrackingService)
        }
    }
}