package com.example.shifthackz.maptest.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.internal.obscura.utils.FragmentTransactionHelper
import com.example.shifthackz.maptest.MapFragment
import com.example.shifthackz.maptest.R

class MainActivity : AppCompatActivity() {

    val fragmentTransactionHelper = FragmentTransactionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentTransactionHelper.addFragment(this, MapFragment.newInstance(), R.id.containerMainScreen, "maps", true);
    }
}
