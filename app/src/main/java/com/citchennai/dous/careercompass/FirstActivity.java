package com.citchennai.dous.careercompass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);


//        final SharedPreferences preferences = getSharedPreferences("careercompass-login",0);
//        final SharedPreferences.Editor editor = preferences.edit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if(preferences.getBoolean("is_login",false)){
//                    startActivity(new Intent(FirstActivity.this,UiFirst.class));
//                }
//                else{
//                    startActivity(new Intent(FirstActivity.this, LoginMain.class));
//                    finish();
//                }

                startActivity(new Intent(FirstActivity.this,UiFirst.class));


            }
        }, 2000);

    }
}
