package com.google.maps.android.compose

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

private val TAG = MapClusteringActivity::class.simpleName

class MapClusteringActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_clustering_activity)
    }
}