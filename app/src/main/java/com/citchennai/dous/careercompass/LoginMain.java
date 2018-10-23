package com.citchennai.dous.careercompass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;

public class LoginMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_main);

        setTitle("Chennai Institute of Technology");

        AppCompatButton login=(AppCompatButton)findViewById(R.id.btn_login_page);
        AppCompatButton signup=(AppCompatButton)findViewById(R.id.btn_signups_page);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMain.this,login.class));
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMain.this,RegisterPage.class));
                finish();
            }
        });
    }
}
