package com.citchennai.dous.careercompass;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by RupaRavulakollu on 20-02-2018.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService{

    String REG_TOKEN = "REG_TOKEN";

    @Override
    public void onTokenRefresh() {
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d("token",recent_token);
    }
}
