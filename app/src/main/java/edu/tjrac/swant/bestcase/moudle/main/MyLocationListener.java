package edu.tjrac.swant.bestcase.moudle.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import javax.security.auth.callback.Callback;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class MyLocationListener implements LifecycleObserver {

    private boolean enable = false;
    private LocationListener mLocationListener;

    public MyLocationListener(Context context, Lifecycle lifecycle, Callback callback) {
        // ...
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
//        var onLocationChange: LocationListener = object : LocationListener {
//            override fun onLocationChanged(location: Location) {
//                city?.text = "$location.longitude,$location.latitude"
//                Log.i("tag", "msg" + location.longitude + "," + location.latitude)
//            }
//
//            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
//            }
//
//            override fun onProviderEnabled(provider: String) {
//            }
//
//            override fun onProviderDisabled(provider: String) {
//            }
//        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {

        // connect to system location service
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        // disconnect from system location service
    }
}
