package com.example.admin.navbottom;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService
    extends FirebaseInstanceIdService

    {
        private static final String TAG = "MyFirebaseInsIDService";

        @Override
        public void onTokenRefresh () {
        //getupdatetoken
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "New Token" + refreshedToken);
    }
    }