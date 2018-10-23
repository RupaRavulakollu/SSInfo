package com.citchennai.dous.careercompass;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Forget_Password extends AppCompatActivity {
    @InjectView(R.id.input_email_for_password)
    EditText _emailText;
    @InjectView(R.id.btn_forget_password)
    AppCompatButton _loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);
        ButterKnife.inject(this);
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    public void login() {

        String email = _emailText.getText().toString();
        login(email);
        // TODO: Implement your own authentication logic here.


    }

    private void login(final String username) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(Forget_Password.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", uname));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://54.179.159.232/wapco/and/f.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }




                return result;
            }

            @Override
            protected void onPostExecute(String result){
                String s = result.trim();
                progressDialog.dismiss();
                if(s.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please check interconnection and try",Toast.LENGTH_LONG).show();
                    return;
                }
                String[] ss=s.split(",");
                switch (ss[0])
                {

                    case "1":
                        Toast.makeText(getApplicationContext(), "Sucessfully Sent Password to your email address", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Forget_Password.this,login.class));
                        finish();
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_SHORT).show();
                }

            }
        }
        if(isNetworkConnected())
        {
            LoginAsync la = new LoginAsync();
            la.execute(username);
        }else{
            Toast.makeText(getApplicationContext(),"Please check interconnection and try",Toast.LENGTH_LONG).show();
        }


    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
