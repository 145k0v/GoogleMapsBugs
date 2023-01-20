package com.google.maps.android.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import kotlin.random.Random

class MapClusteringFragment : Fragment() {

    private val navigationController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ComposeView {
        return ComposeView(requireContext()).apply {
            setContent { GoogleMapClustering() }
        }
    }

    @Composable
    fun GoogleMapClustering() {
        val items = remember { mutableStateListOf<MyItem>() }
        LaunchedEffect(Unit) {
            for (i in 1..1000) {
                val position = LatLng(
                    singapore2.latitude + Random.nextFloat(),
                    singapore2.longitude + Random.nextFloat(),
                )
                items.add(MyItem(position, "Marker", "Snippet"))
            }
        }
        GoogleMapClustering(items = items)
    }

    @OptIn(MapsComposeExperimentalApi::class)
    @Composable
    fun GoogleMapClustering(items: List<MyItem>) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            val context = LocalContext.current
            var clusterManager by remember { mutableStateOf<ClusterManager<MyItem>?>(null) }
            MapEffect(items) { map ->
                if (clusterManager == null) {
                    clusterManager = ClusterManager<MyItem>(context, map).apply {
                        renderer = GoogleMarkerRenderer(context, map, this)
                        setOnClusterItemClickListener {
                            navigationController.navigate(R.id.someOtherFragment)
                            true
                        }
                    }
                }

                map.setOnCameraIdleListener {
                    clusterManager?.onCameraIdle()
                }

                clusterManager?.clearItems()
                clusterManager?.addItems(items)
                clusterManager?.cluster()
            }
//        LaunchedEffect(key1 = cameraPositionState.isMoving) {
//            if (!cameraPositionState.isMoving) {
//                clusterManager?.onCameraIdle()
//            }
//        }
            MarkerInfoWindow(
                state = rememberMarkerState(position = singapore),
                onClick = {
                    // This won't work :(
//                    Log.d(TAG, "I cannot be clicked :( $it")
                    true
                }
            )
        }
    }
}