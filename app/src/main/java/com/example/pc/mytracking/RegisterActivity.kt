package com.example.pc.mytracking

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.pc.mytracking.location.GoogleApiConnectionCallback
import com.example.pc.mytracking.location.GoogleSignInClient
import com.example.pc.mytracking.util.RequestCode
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_register.*

/**
 *
 *Created by CaoVanCuong on 11/1/2017.
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var mGoogleApiClient: GoogleApiClient
    private var mGoogleApiClientConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mGoogleApiClient = GoogleSignInClient.getInstance(this, object : GoogleApiConnectionCallback {
            override fun onConnectionFailed(p0: ConnectionResult) {
                mGoogleApiClientConnected = false
            }

            override fun onConnected(p0: Bundle?) {
                mGoogleApiClientConnected = true
            }

            override fun onConnectionSuspended(p0: Int) {
                mGoogleApiClientConnected = false
            }
        })
        mGoogleApiClient.connect()
        btnSignIn.setOnClickListener {
            signIn()
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RequestCode.GOOGLE_SIGIN_REQUEST) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun signIn() {
        if (mGoogleApiClientConnected) {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RequestCode.GOOGLE_SIGIN_REQUEST)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d("tag11", "handleSignInResult:" + result.isSuccess)
        if (result.isSuccess) {
            // Signed in successfully, show authenticated UI.
            val acct = result.signInAccount
            Log.i("tag11", "name:" + acct?.displayName)
        }
    }
}