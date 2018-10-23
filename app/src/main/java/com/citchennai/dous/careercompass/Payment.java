package com.citchennai.dous.careercompass;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Payment extends AppCompatActivity {

    WebView webView;
    SharedPreferences sharedPreferences,sharedPreferences_update;
    SharedPreferences.Editor editor;
   // Button payment;

    private ProgressDialog prDialog;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        String user_mail=getSharedPreferences("email", Context.MODE_PRIVATE).getString("user_email","");
        //Toast.makeText(this, "pay"+user_mail, Toast.LENGTH_SHORT).show();
       // payment=findViewById(R.id.pay);
        webView=findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.addJavascriptInterface(new WebInterface(Payment.this),"Android");
        webView.loadUrl("https://cittechfest.000webhostapp.com/careercompass/select_slots.php?email="+user_mail);
       // webView.loadUrl("http://192.168.43.220/careercompass/select_slots.php?email="+user_mail);
       sharedPreferences= getSharedPreferences("payment",Context.MODE_PRIVATE);
       editor=sharedPreferences.edit();
       editor.putBoolean("_paid",true)
               .commit();
       //getSharedPreferences("change",Context.MODE_PRIVATE).edit().putString("sessions","updated").commit();


//        if(sharedPreferences.getBoolean("_paid",false))
//        {
//            startActivity(new Intent(Payment.this,UiFirst.class));
//            finish();
//        }




    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            prDialog = new ProgressDialog(Payment.this);
            prDialog.setMessage("Please wait ...");
            prDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (prDialog != null) {
                prDialog.dismiss();
            }
        }
    }

}
