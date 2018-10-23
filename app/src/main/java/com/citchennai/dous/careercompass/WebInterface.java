package com.citchennai.dous.careercompass;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by RupaRavulakollu on 09-02-2018.
 */

public class WebInterface {


    Context context;
    public  WebInterface(Context context){
        this.context=context;
    }

    @JavascriptInterface
    public void showMessage(String message)
    {
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
        if(context.getSharedPreferences("payment",Context.MODE_PRIVATE).getBoolean("_paid",false))
        {
            context.getSharedPreferences("button",Context.MODE_PRIVATE).edit().putBoolean("visibility",true).commit();
            context.startActivity(new Intent(context,UiFirst.class));

        }

    }
}
