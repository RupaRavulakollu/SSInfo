package com.citchennai.dous.careercompass;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AgendaActivity extends AppCompatActivity {
    ProgressDialog prDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        WebView webView = findViewById(R.id.agendaview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        //webView.loadUrl(getIntent().getExtras().get("profile_url").toString());
        //webView.loadUrl("file:///android_asset/agenda.html");
//        String[] keys = new String[]{
//                "profile_url",
//                "about_us",
//                "feedback",
//                "theme"
//        };
//
//        for(int i=0; i<keys.length; i++)
//        {
//            switch (keys[i]){
//                case "profile_url" :
//                    Object rupa=getIntent().getExtras().get("profile_url");
//                    if(rupa!=null)
//                    webView.loadUrl(rupa.toString());
//                    break;
//                case "about_us" :
//                    Object rupa1=getIntent().getExtras().get("about_us");
//                    if(rupa1!=null)
//                    webView.loadUrl(rupa1.toString());
//                    break;
//                case "feedback" :
//                    Object rupa2=getIntent().getExtras().get("feedback");
//                    if(rupa2!=null)
//                    webView.loadUrl(rupa2.toString());
//                    break;
//                case "theme" :
//                    Object rupa3=getIntent().getExtras().get("theme");
//                    if(rupa3!=null)
//                    webView.loadUrl(rupa3.toString());
//                    break;
//            }
        //}
        webView.loadUrl(getIntent().getExtras().get("url").toString());


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
            prDialog = new ProgressDialog(AgendaActivity.this);
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
