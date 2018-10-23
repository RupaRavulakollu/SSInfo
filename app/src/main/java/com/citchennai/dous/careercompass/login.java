package com.citchennai.dous.careercompass;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class login extends Activity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_login)
    Button _loginButton;
    @InjectView(R.id.frogetpassword)
    TextView fglnk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login2);

        ButterKnife.inject(this);
        pref = getApplicationContext().getSharedPreferences("careercompass-login", 0); // 0 - for private mode
        editor = pref.edit();
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        fglnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,Forget_Password.class));
            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
            return;
        }
        String email = _emailText.getText().toString();
        //getSharedPreferences("email",Context.MODE_PRIVATE).edit().putString("user_email",email).commit();
        String password = _passwordText.getText().toString();
        login(email,password);
        // TODO: Implement your own authentication logic here.


    }

    private void login(final String username, final String password){
       String url = "https://cittechfest.000webhostapp.com/careercompass/login.php";
        //String url = "http://192.168.43.220/careercompass/login.php";

        final ProgressDialog progressDialog=new ProgressDialog(login.this);
        progressDialog.setMessage("Authenticating...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        StringRequest request =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response_http) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String response_array[]=response_http.split(":");
                        String response="";
                        if(response_array.length>0)
                        {
                            response=response_array[0].trim();
                        }
                        Log.e("RUPPAPAFDIUGHOI",response);
                        if (response.equals("sucess_payment")){

                            progressDialog.dismiss();
                            Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            editor.putBoolean("is_login", true);
                            editor.commit();
                            getSharedPreferences("user",Context.MODE_PRIVATE).edit().putString("name",response_array[1]).commit();

                            getSharedPreferences("registration",Context.MODE_PRIVATE).edit().putBoolean("register",true).commit();
                            getSharedPreferences("button",Context.MODE_PRIVATE).edit().putBoolean("visibility",true).commit();


                            getSharedPreferences("email",Context.MODE_PRIVATE).edit().putString("user_email",username).commit();


                            startActivity(new Intent(login.this,UiFirst.class));
                            finish();

                        }else if(response.equals("sucess")) {
                            progressDialog.dismiss();
                            Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            editor.putBoolean("is_login", true);
                            editor.commit();
                            getSharedPreferences("user",Context.MODE_PRIVATE).edit().putString("name",response_array[1]).commit();
                            getSharedPreferences("registration",Context.MODE_PRIVATE).edit().putBoolean("register",true).commit();


                            getSharedPreferences("email",Context.MODE_PRIVATE).edit().putString("user_email",username).commit();


                            startActivity(new Intent(login.this,UiFirst.class));
                            finish();


                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(login.this, "Email and password do not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "Try Again"+error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("username",username);
                map.put("password",password);
                return map;
            }
        };
        if(isNetworkConnected())
        {
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        }else{
            Toast.makeText(getApplicationContext(),"Please check interconnection and try",Toast.LENGTH_LONG).show();
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        super.onBackPressed();
        //moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


}
