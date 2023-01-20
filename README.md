# Google Maps Android Bugs

## DefaultClusterRenderer crashes frequently

To reproduce:
1. Open "Map Clustering"
2. Tap on an item to open another Fragment
3. Tap "Back" to return to the map
4. Move map, tap on any other item, go back, etc.

Eventually, the app will crash with the following stacktrace:
```
java.lang.IllegalArgumentException: Unmanaged descriptor
   	at com.google.maps.api.android.lib6.common.m.c(:com.google.android.gms.dynamite_mapsdynamite@225014045@22.50.14 (190408-0):0)
    at com.google.maps.api.android.lib6.impl.w.c(:com.google.android.gms.dynamite_mapsdynamite@225014045@22.50.14 (190408-0):1)
    at com.google.maps.api.android.lib6.impl.cy.u(:com.google.android.gms.dynamite_mapsdynamite@225014045@22.50.14 (190408-0):2)
    at com.google.android.gms.maps.model.internal.p.ba(:com.google.android.gms.dynamite_mapsdynamite@225014045@22.50.14 (190408-0):42)
    at fd.onTransact(:com.google.android.gms.dynamite_mapsdynamite@225014045@22.50.14 (190408-0):4)
    at android.os.Binder.transact(Binder.java:1084)
    at com.google.android.gms.internal.maps.zza.zzc(com.google.android.gms:play-services-maps@@18.0.0:2)
    at com.google.android.gms.internal.maps.zzv.zzs(com.google.android.gms:play-services-maps@@18.0.0:3)
    at com.google.android.gms.maps.model.Marker.setIcon(com.google.android.gms:play-services-maps@@18.0.0:2)
    at com.google.maps.android.compose.GoogleMarkerRenderer.onClusterItemUpdated(GoogleMarkerRenderer.kt:43)
    at com.google.maps.android.compose.GoogleMarkerRenderer.onClusterItemUpdated(GoogleMarkerRenderer.kt:12)
    at com.google.maps.android.clustering.view.DefaultClusterRenderer$CreateMarkerTask.perform(DefaultClusterRenderer.java:1060)
    at com.google.maps.android.clustering.view.DefaultClusterRenderer$CreateMarkerTask.access$2400(DefaultClusterRenderer.java:1021)
    at com.google.maps.android.clustering.view.DefaultClusterRenderer$MarkerModifier.performNextTask(DefaultClusterRenderer.java:738)
    at com.google.maps.android.clustering.view.DefaultClusterRenderer$MarkerModifier.handleMessage(DefaultClusterRenderer.java:707)
    at android.os.Handler.dispatchMessage(Handler.java:106)
    at android.os.Looper.loopOnce(Looper.java:233)
    at android.os.Looper.loop(Looper.java:344)
    at android.app.ActivityThread.main(ActivityThread.java:8248)
    at java.lang.reflect.Method.invoke(Native Method)
    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:589)
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1071)
```
