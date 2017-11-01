package com.example.pc.mytracking.location

import android.content.Context
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices

/**
 *
 * Created by CaoVanCuong on 10/31/2017.
 */
class GoogleLocationClient private constructor() {

    companion object {
        private var sInstance: GoogleApiClient? = null

        fun getInstance(context: Context, listener: GoogleApiConnectionCallback): GoogleApiClient {
            if (sInstance == null) {
                sInstance = GoogleApiClient.Builder(context)
                        .addConnectionCallbacks(listener)
                        .addOnConnectionFailedListener(listener)
                        .addApi(LocationServices.API)
                        .build()
            }
            return sInstance!!
        }
    }

}