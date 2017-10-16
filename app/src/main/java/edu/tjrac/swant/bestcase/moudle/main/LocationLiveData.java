package edu.tjrac.swant.bestcase.moudle.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class LocationLiveData extends LiveData<Location> {

    private static LocationLiveData sInstance;
    private LocationManager locationManager;

    public static LocationLiveData get(Context context){
        if(sInstance==null){
            sInstance=new LocationLiveData(context.getApplicationContext());
        }
        return sInstance;
    }
    private LocationLiveData(Context context){
        locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
    }
    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            setValue(location);
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

    @SuppressLint("MissingPermission")
    @Override
    protected void onActive() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
    }

    @Override
    protected void onInactive() {
        locationManager.removeUpdates(listener);
    }

}
