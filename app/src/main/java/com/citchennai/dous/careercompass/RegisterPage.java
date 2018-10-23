package com.citchennai.dous.careercompass;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {

    EditText username, DateOfBirth, mobile_num, mail, designation, institution_addr, communication_addr, qualification, experience, edit_password;
    Button submitbt;
    RadioGroup rad_group;
    RadioButton internal_stu,external_stu;
    Calendar myCalendar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    String url="https://cittechfest.000webhostapp.com/careercompass/";
    String mail_url= "https://cittechfest.000webhostapp.com/careercompass/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        InputFilter filter = new InputFilter.LengthFilter(10);
        username=findViewById(R.id.username);
        DateOfBirth=findViewById(R.id.DOB);
        mobile_num=findViewById(R.id.mobile);
        mobile_num.setFilters(new InputFilter[]{filter});
        mail=findViewById(R.id.gmail);
        designation=findViewById(R.id.designation);
        institution_addr=findViewById(R.id.institution);
        communication_addr=findViewById(R.id.address);
        qualification=findViewById(R.id.qualificaton);
        experience=findViewById(R.id.experience);
        edit_password=findViewById(R.id.password);

        rad_group=findViewById(R.id.radioGroup);
        internal_stu = rad_group.findViewById(R.id.intern);
        external_stu = rad_group.findViewById(R.id.ext);

        submitbt = findViewById(R.id.submit);



        submitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();



            }
        });

         myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        DateOfBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegisterPage.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });





    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        DateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "SignUp failed", Toast.LENGTH_LONG).show();

        submitbt.setEnabled(true);
    }


    public void signup() {


        if (!validate()) {
            onSignupFailed();
            return;
        }
        //Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        final ProgressDialog progressDialog = new ProgressDialog(RegisterPage.this);

        String name = username.getText().toString();
        String email = mail.getText().toString();
        String password = edit_password.getText().toString();
        String str_phone = mobile_num.getText().toString();
        String str_designation = designation.getText().toString();
        String str_institution = institution_addr.getText().toString();
        String str_communication = communication_addr.getText().toString();
        String str_qualification = qualification.getText().toString();
        String str_experience = experience.getText().toString();
        String stu_category;

        if(internal_stu.isChecked())
        {
            stu_category = internal_stu.getText().toString();
        }
        else{
            stu_category = external_stu.getText().toString();
        }


        // TODO: Implement your own signup logic here.
       Signup(name,email,password,str_phone,str_designation,str_institution,str_communication,str_qualification,str_experience,stu_category);

    }

    public void onSignupSuccess() {
        submitbt.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }


    private void Signup(final String name, final String email, final String password, final String str_phone, final String str_designation, final String str_institution, final String str_communication, final String str_qualification, final String str_experience, final String stu_category) {

        final ProgressDialog progressDialog=new ProgressDialog(RegisterPage.this);
        progressDialog.setMessage("Authenticating...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();




//        uiFirst.btn_login = findViewById(R.id.btn_login_page);
//        uiFirst.btn_signup = findViewById(R.id.btn_signups_page);
//        uiFirst.btn_confirm_reg = findViewById(R.id.confirm_reg);
//
//        uiFirst.btn_login.setVisibility(View.GONE);
//        uiFirst.btn_signup.setVisibility(View.GONE);
//        uiFirst.btn_confirm_reg.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url+"register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                sharedPreferences = getSharedPreferences("registration",Context.MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putBoolean("register",true);
                editor.commit();

                Toast.makeText(RegisterPage.this, "Successful", Toast.LENGTH_SHORT).show();
                Log.e("response",response.toString());
                getSharedPreferences("email",Context.MODE_PRIVATE).edit().putString("user_email",email).commit();
                getSharedPreferences("user",Context.MODE_PRIVATE).edit().putString("name",name).commit();
                startActivity(new Intent(RegisterPage.this,UiFirst.class));
                new AsyncTaskClass(RegisterPage.this).execute(name,email);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterPage.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<String,String>();
                hashMap.put("name",name);
                hashMap.put("dob",DateOfBirth.getText().toString());
                hashMap.put("mail",email);
                hashMap.put("designation",str_designation);
                hashMap.put("institution",str_institution);
                hashMap.put("address",str_communication);
                hashMap.put("qualification",str_qualification);
                hashMap.put("experience",str_experience);
                hashMap.put("phonenumber",str_phone);
                hashMap.put("password",password);
                hashMap.put("category",stu_category);
                return hashMap;
            }
        };
        if (isNetworkConnected()) {
//            SignupAs la = new SignupAsync();
//            la.execute(name,email,password,str_phone,str_designation,str_institution,str_communication,str_qualification,str_experience);
            RequestQueue requestQueue = Volley.newRequestQueue(RegisterPage.this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(getApplicationContext(), "Please check interconnection and try", Toast.LENGTH_LONG).show();
        }
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }



    public boolean validate() {
        boolean valid = true;

        String name = username.getText().toString();
        String email = mail.getText().toString();
        String password = edit_password.getText().toString();
        String str_phone = mobile_num.getText().toString();
        String str_designation = designation.getText().toString();
        String str_institution = institution_addr.getText().toString();
        String str_communication = communication_addr.getText().toString();
        String str_qualification = qualification.getText().toString();
        String str_experience = experience.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            username.setError("at least 3 characters");
            valid = false;
        } else {
            username.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mail.setError("enter a valid email address");
            valid = false;
        } else {
            mail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edit_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edit_password.setError(null);
        }

        if (str_phone.isEmpty() || str_phone.length() < 10) {
            mobile_num.setError("at least 3 characters");
            valid = false;
        } else {
            mobile_num.setError(null);
        }
//

        if (str_designation.isEmpty() || str_designation.length() < 3) {
            designation.setError("at least 3 characters");
            valid = false;
        } else {
            designation.setError(null);
        }



        if (str_institution.isEmpty() || str_institution.length() < 3) {
            institution_addr.setError("at least 3 characters");
            valid = false;
        } else {
            institution_addr.setError(null);
        }

        if (str_communication.isEmpty() || str_communication.length() < 3) {
            communication_addr.setError("at least 3 characters");
            valid = false;
        } else {
            communication_addr.setError(null);
        }



        if (str_qualification.isEmpty() || str_qualification.length() < 2) {
            qualification.setError("at least 2 characters");
            valid = false;
        } else {
            qualification.setError(null);
        }



        if (str_experience.isEmpty() || str_experience.length() > 3) {
            experience.setError("not more than 3 characters");
            valid = false;
        } else {
            experience.setError(null);
        }

        return valid;
    }


    class AsyncTaskClass extends AsyncTask<Object,Object,Object> {
        Context context;

        public AsyncTaskClass(Context context) {
            this.context = context;
        }

        @Override
        protected Object doInBackground(final Object... objects) {
            StringRequest request = new StringRequest(Request.Method.POST, mail_url+"email.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Mail sent", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "mail not sent", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<>();
                    map.put("name",objects[0].toString());
                    map.put("email",objects[1].toString());
                    return map;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(context);
            requestQueue.add(request);
            return null;
        }
    }

}
