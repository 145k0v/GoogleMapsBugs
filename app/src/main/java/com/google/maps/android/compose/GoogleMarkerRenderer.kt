package com.google.maps.android.compose

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

internal class GoogleMarkerRenderer(
    context: Context,
    map: GoogleMap,
    manager: ClusterManager<MyItem>,
) : DefaultClusterRenderer<MyItem>(context, map, manager) {

    private val markerIconFactory = MarkerIconFactory(context)

    override fun onBeforeClusterRendered(cluster: Cluster<MyItem>, markerOptions: MarkerOptions) {
        super.onBeforeClusterRendered(cluster, markerOptions)
        val icon = markerIconFactory.createClusterIcon(cluster.text()).let(BitmapDescriptorFactory::fromBitmap)
        markerOptions.icon(icon)
    }

    override fun onClusterUpdated(cluster: Cluster<MyItem>, marker: Marker) {
        super.onClusterUpdated(cluster, marker)
        val icon = markerIconFactory.createClusterIcon(cluster.text()).let(BitmapDescriptorFactory::fromBitmap)
        marker.setIcon(icon)
    }

    override fun onBeforeClusterItemRendered(item: MyItem, markerOptions: MarkerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions)
        val icon = markerIconFactory.createItemIcon(item, selected = false)
            .let(BitmapDescriptorFactory::fromBitmap)
        markerOptions.icon(icon)
    }

    override fun onClusterItemUpdated(item: MyItem, marker: Marker) {
        super.onClusterItemUpdated(item, marker)
        val icon = markerIconFactory.createItemIcon(item, selected = false)
            .let(BitmapDescriptorFactory::fromBitmap)
        marker.setIcon(icon)
    }

    override fun shouldRenderAsCluster(cluster: Cluster<MyItem>) = cluster.size > 1

    private fun Cluster<*>.text(): String {
        if (size > MaxClusterSize) {
            return "$MaxClusterSize+"
        }
        return size.toString()
    }

    private companion object {
        private const val MaxClusterSize = 99
    }
}