package com.example.shifthackz.maptest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback


open class MapFragment : BaseFragment(), View.OnClickListener, OnMapReadyCallback,
    GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveStartedListener {
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapReady(p0: GoogleMap?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCameraMove() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCameraMoveStarted(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    fun MapFragment() {}

    companion object {
        @JvmStatic
        fun newInstance() =
            MapFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
