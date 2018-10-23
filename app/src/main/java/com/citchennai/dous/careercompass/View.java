package com.citchennai.dous.careercompass;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class View extends AppCompatActivity {
public String url_d="http://saran.cu.cc/wabco/api/dn.php";
    ListView listView;
    LinkedHashMap<String,HashMap<String, String>> jsonhlp=new LinkedHashMap<String, HashMap<String, String>> ();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        android.support.v7.app.ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        startService(new Intent(getApplicationContext(),Chat_Icon_Service.class));
        setTitle("Gallery");
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            try {
                url_d=url_d+"?year="+extras.getString("year")+"&branch="+extras.getString("branch")+"&sem="+URLEncoder.encode(extras.getString("sem"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                url_d=url_d+"?year="+extras.getString("year")+"&branch="+extras.getString("branch")+"&sem="+extras.getString("sem").replace(" ","%20");
            }

        }
        Showit s=new Showit();
        s.execute();
        listView= (ListView) findViewById(R.id.onlist);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabdall);



        fab.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Toast.makeText(View.this, "Please Wait Download will start in few seconds......", Toast.LENGTH_LONG).show();
                new download_all().execute();
                //new image_saveing().execute();
            }
        });
    }

    //start of json
    private class Showit extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;
        JSONObject cl;





        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(final Boolean success) {




            // select single ListView item
//            View header = getLayoutInflater().inflate(R.layout.activity_main, null);
           // listView.setAdapter(new CustomAdapter(View.this,jsonhlp));
//             lv.addHeaderView(header);
        }

        @Override
        protected Boolean doInBackground(final String... args) {

            JSONParser jParser = new JSONParser();

            // get JSON data from URL
            try{


            JSONArray json = jParser.getJSONFromUrl(url_d);

            for (int i = 0; i < json.length(); i++) {
                try {
                    JSONObject c = json.getJSONObject(i);
                    JSONArray jsonsub=c.getJSONArray("data");
                    String title=c.getString("name");
                    //String vtread = c.getString(TREAD);
                    HashMap<String, String> map = new HashMap<String, String>();
                    // Add child node to HashMap key & value
                    for (int i1 = 0; i1 < jsonsub.length(); i1++) {
                        cl = jsonsub.getJSONObject(i1);
                        map.put(cl.getString("name"),cl.getString("icon"));
                    }
                    jsonhlp.put(title,map);


                   // map.clear();
                    //map.put(TREAD, vtread);
                    //jsonlista.add(map);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }catch (Exception eeee)
            {
                eeee.printStackTrace();
            }
            return null;
        }
    }
    //end of json

    //start of displaying






    //end of displaying












    //starting for download all



    class download_all extends AsyncTask<String, Void, Void> {


        private String pth;
        private String nm;

        protected Void doInBackground(String... urls) {
            JSONParser jParser = new JSONParser();
            for(Map.Entry<String, HashMap<String, String>> tympp:jsonhlp.entrySet())
            {
                for(Map.Entry<String,String> ttmp:tympp.getValue().entrySet())
                {
                    JSONObject json = null;
                    try {
                        json = jParser.getJSONFromUrls("http://saran.cu.cc/wabco/api/info.php?q="+ URLEncoder.encode(ttmp.getValue(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    try {
                        pth= json.getString("path");
                        nm=json.getString("name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        DownloadFromUrl(ttmp.getValue(),pth,ttmp.getKey());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            return null;
        }


    }
    public void DownloadFromUrl(String DownloadUrl, String dir,String fileName) throws Exception
    {

        //String dir_card = Environment.getExternalStorageDirectory().getAbsolutePath()+"/eng_drm/"+dir;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DownloadUrl));
        request.setDescription("M&L WORKSHOP");
        request.setTitle(fileName);
// in order for this if to run, you must use the android 3.2 to compile your app

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir("/wabco_workshop/"+dir+"/",fileName);
        request.setVisibleInDownloadsUi(false);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);


        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }

    //end of download all

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
