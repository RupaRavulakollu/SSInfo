package com.citchennai.dous.careercompass;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.DimType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.Random;
import java.util.TreeMap;



public class UiFirst extends AppCompatActivity implements
        BoomMenuButton.OnSubButtonClickListener,
        BoomMenuButton.AnimatorListener,
        View.OnClickListener{

private BoomMenuButton boomMenuButtonInActionBar;
private BoomMenuButton boomInfo;

private Context mContext;
private View mCustomView;
        AppCompatButton btn_login,btn_signup,btn_confirm_reg,session_update;

private boolean isInit=false;
        TreeMap<Integer, String[]> list_of_partipi;
        boolean isfxx=false;
        SharedPreferences pref,preferences1;
        private SharedPreferences.Editor editor;
        SharedPreferences.Editor editor1;

        @Override
protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_first);
try{
        stopService(new Intent(UiFirst.this, Chat_Icon_Service.class));
}catch (Exception eeee)
{
}



         pref = getApplicationContext().getSharedPreferences("careercompass-login", 0); // 0 - for private mode
         editor = pref.edit();


         btn_login=findViewById(R.id.btn_login_page);
         btn_signup=findViewById(R.id.btn_signups_page);
         btn_confirm_reg=findViewById(R.id.confirm_reg);
         session_update=findViewById(R.id.update_slots);

          preferences1= getSharedPreferences("registration",Context.MODE_PRIVATE);

          if(preferences1.getBoolean("register",false))
          {
                btn_login.setVisibility(View.GONE);
                btn_signup.setVisibility(View.GONE);
                btn_confirm_reg.setVisibility(View.VISIBLE);
          }

          if(getSharedPreferences("button",Context.MODE_PRIVATE).getBoolean("visibility",false))
          {
                  btn_confirm_reg.setVisibility(View.GONE);
                  session_update.setVisibility(View.VISIBLE);

          }









        btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        startActivity(new Intent(UiFirst.this,login.class));
                }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        startActivity(new Intent(UiFirst.this,RegisterPage.class));

                }
        });

        btn_confirm_reg.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                        startActivity(new Intent(UiFirst.this,Payment.class));

                }
        });

        session_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        startActivity(new Intent(UiFirst.this,Payment.class));
                }
        });



//        if(!pref.getBoolean("is_login", false))
//        {
//                Intent intent = new Intent(UiFirst.this, FirstActivity.class);
//                super.onStop();
//                finish();
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                startActivity(intent);
//                finish();
//                isfxx=true;
//        }


        mContext=this;
        ActionBar mActionBar=getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater=LayoutInflater.from(this);
                mActionBar.setTitle("CAREER COMPASS 2018");
        mCustomView=mInflater.inflate(R.layout.custom_actionbar,null);
        TextView mTitleTextView=(TextView)mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("CAREER COMPASS 2018");

        boomMenuButtonInActionBar= mCustomView.findViewById(R.id.boom);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        list_of_partipi = new TreeMap<>();
        ((Toolbar)mCustomView.getParent()).setContentInsetsAbsolute(0,0);
        String data_temp[]=new String[5];
//        boomMenuButton=(BoomMenuButton)findViewById(R.id.boom);

        boomInfo= mCustomView.findViewById(R.id.info);

        initViews();
        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                        if(!isfxx)
                        boomMenuButtonInActionBar.boom();

                }
        }, 1000);

        }

@Override
public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        if(!isInit){
        initBoom();
        initInfoBoom();
        }
        isInit=true;
        }

private void initInfoBoom(){

        if(getSharedPreferences("careercompass-login",Context.MODE_PRIVATE).getBoolean("is_login",false) ||
                getSharedPreferences("registration",Context.MODE_PRIVATE).getBoolean("register",false))
        {
                Drawable[]drawables=new Drawable[2];
                int[]drawablesResource=new int[]{
                        R.drawable.instruction,
                        R.drawable.logout
                };
                for(int i=0;i<2;i++)
                        drawables[i]= ContextCompat.getDrawable(mContext,drawablesResource[i]);

                int[][]colors=new int[2][2];
                for(int i=0;i<2;i++){
                        colors[i][1]= ContextCompat.getColor(mContext,R.color.gray);
                        colors[i][0]=Util.getInstance().getPressedColor(colors[i][1]);
                }

                // Now with Builder, you can init BMB more convenient
                new BoomMenuButton.Builder()
                        .subButtons(drawables,colors,new String[]{"Instructions","Logout"})
                        .button(ButtonType.HAM)
                        .boom(BoomType.PARABOLA)
                        .place(PlaceType.HAM_2_1)
                        .subButtonsShadow(Util.getInstance().dp2px(2),Util.getInstance().dp2px(2))
                        .subButtonTextColor(ContextCompat.getColor(mContext,R.color.black))
                        .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener(){
                                @Override
                                public void onClick(int buttonIndex){
                                        if(buttonIndex==1){
                                                //startActivity(new Intent(UiFirst.this,Codeofcontact.class));
                                                editor.putBoolean("is_login", false);
                                                editor.commit();

                                                getSharedPreferences("registration",Context.MODE_PRIVATE).edit().putBoolean("register",false).commit();
                                                getSharedPreferences("button",Context.MODE_PRIVATE).edit().putBoolean("visibility",false).commit();

                                                btn_login.setVisibility(View.VISIBLE);
                                                btn_signup.setVisibility(View.VISIBLE);
                                                //startActivity(new Intent(UiFirst.this,LoginMain.class));
                                                Intent intent = getIntent();
                                                finish();
                                                startActivity(intent);
                                        }
                                        if(buttonIndex==0){
                                                startActivity(new Intent
                                                        (UiFirst.this,AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/Instructions.htm"));
                                        }
                                        //else if(buttonIndex==2){
//                editor.putBoolean("is_login", false);
//                editor.commit();
//                startActivity(new Intent(UiFirst.this,LoginMain.class));
//                finish();
//        }
                                }
                        })
                        .init(boomInfo);
        }else{
                Drawable[]drawables=new Drawable[1];
                int[]drawablesResource=new int[]{
                        R.drawable.instruction,
                       // R.drawable.logout
                };
                for(int i=0;i<1;i++)
                        drawables[i]= ContextCompat.getDrawable(mContext,drawablesResource[i]);

                int[][]colors=new int[1][2];
                for(int i=0;i<1;i++){
                        colors[i][1]= ContextCompat.getColor(mContext,R.color.gray);
                        colors[i][0]=Util.getInstance().getPressedColor(colors[i][1]);
                }

                // Now with Builder, you can init BMB more convenient
                new BoomMenuButton.Builder()
                        .subButtons(drawables,colors,new String[]{"Instructions"})
                        .button(ButtonType.HAM)
                        .boom(BoomType.PARABOLA)
                        .place(PlaceType.HAM_1_1)
                        .subButtonsShadow(Util.getInstance().dp2px(2),Util.getInstance().dp2px(2))
                        .subButtonTextColor(ContextCompat.getColor(mContext,R.color.black))
                        .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener(){
                                @Override
                                public void onClick(int buttonIndex){
//                                        if(buttonIndex==1){
//                                                //startActivity(new Intent(UiFirst.this,Codeofcontact.class));
//                                                editor.putBoolean("is_login", false);
//                                                editor.commit();
//
//                                                getSharedPreferences("registration",Context.MODE_PRIVATE).edit().putBoolean("register",false).commit();
//                                                getSharedPreferences("button",Context.MODE_PRIVATE).edit().putBoolean("visibility",false).commit();
//
//                                                btn_login.setVisibility(View.VISIBLE);
//                                                btn_signup.setVisibility(View.VISIBLE);
//                                                //startActivity(new Intent(UiFirst.this,LoginMain.class));
//                                                Intent intent = getIntent();
//                                                finish();
//                                                startActivity(intent);
//                                        }
                                        if(buttonIndex==0){
                                                startActivity(new Intent
                                                        (UiFirst.this,AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/Instructions.htm"));
                                        }
                                        //else if(buttonIndex==2){
//                editor.putBoolean("is_login", false);
//                editor.commit();
//                startActivity(new Intent(UiFirst.this,LoginMain.class));
//                finish();
//        }
                                }
                        })
                        .init(boomInfo);
        }


        }


private void initBoom(){
        int number=6;

        Drawable[]drawables=new Drawable[number];
        int[]drawablesResource=new int[]{
        R.drawable.calendar,
       // R.drawable.mapmarker,
        //R.drawable.contact_new,
        R.drawable.twitter,
        //R.drawable.galeryyy,
        //R.drawable.stethoscope,
        //R.drawable.hotel,
        //R.drawable.hospital,
        R.drawable.voice,
        R.drawable.aboutus,
        R.drawable.themes,
        R.drawable.feedback
        };

        for(int i=0;i<number;i++)
        drawables[i]= ContextCompat.getDrawable(mContext,drawablesResource[i]);

        String[]STRINGS=new String[]{
//        "AGENDA",
        //"ATTRACTION",
        //"PARTICIPANTS",
        "TWEET",
       // "GALLERY",
        //"MEDICAL",
        //"LODGING",
        //"SAFETY CODE",
        "SUPPORT",
        "ABOUT US",
        "THEME",
        "FEEDBACK"
        };
        String[]strings=new String[number];
        for(int i=0;i<number;i++)
        strings[i]=STRINGS[i];

        int[][]colors=new int[number][2];
        for(int i=0;i<number;i++){
        colors[i][1]=GetRandomColor();
        colors[i][0]=Util.getInstance().getPressedColor(colors[i][1]);
        }

        ButtonType buttonType=ButtonType.CIRCLE;



        // Now with Builder, you can init BMB more convenient
        new BoomMenuButton.Builder()
        .subButtons(drawables,colors,strings)
        .button(buttonType)
        .boom(BoomType.PARABOLA)
        .place(PlaceType.CIRCLE_6_3)
        .subButtonsShadow(Util.getInstance().dp2px(2),Util.getInstance().dp2px(2))
        .onSubButtonClick(this)
        .animator(this)
        .dim(DimType.DIM_0)
        .init(boomMenuButtonInActionBar);
        }

private void initViews() {


}

private String[]Colors={
        "#F44336",
        "#E91E63",
        "#9C27B0",
        "#2196F3",
        "#03A9F4",
        "#00BCD4",
        "#009688",
        "#4CAF50",
        "#8BC34A",
        "#CDDC39",
        //"#FFEB3B",//yellow
        //"#FFC107",//yellow
        "#FF9800",
        "#FF5722",
        //"#795548", //brown
        "#9E9E9E",
        "#607D8B"};

public int GetRandomColor(){
        Random random=new Random();
        int p=random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
        }
        @Override
        public void onDestroy()
        {
                if(isMyServiceRunning(Service.class))
                        stopService(new Intent(getApplicationContext(), Chat_Icon_Service.class));
                super.onDestroy();
        }
@Override
public void onClick(int buttonIndex){
        switch (buttonIndex)
        {
                case 0:
                        //startActivity(new Intent(UiFirst.this,MainActivity_events.class));
                        startActivity(new Intent(this,Agenda.class));
                        break;

                case 1:
                        startActivity(new Intent(this, MainChatActivity.class));
                        break;



                case 2:
                        startActivity(new Intent(UiFirst.this,SUpport_Ui.class));
                        break;
                case 3 :
                        startActivity(new Intent(UiFirst.this,AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/Chennai%20Institute%20of%20Technology.htm"));
                        break;
                case 4 :
                        startActivity(new Intent(UiFirst.this,AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/theme.htm"));
                        break;


                case 5:
                        startActivity(new Intent(UiFirst.this,AgendaActivity.class).putExtra("url","https://docs.google.com/forms/d/1XjILW9HNzQaJUQrvDJ99ALJyOzNtK-iEdhYwzARWYKo/edit"));
                        break;
        }


        }

boolean pause=false;
        @Override
        public void onPause() {
                super.onPause();
                pause=true;

        }
        @Override
        public void onResume() {
                super.onResume();
                try{
                        stopService(new Intent(UiFirst.this, Chat_Icon_Service.class));
                }catch (Exception eeee)
                {

                }
                if(pause)
                {
                        boomMenuButtonInActionBar.boom();
                }

        }
        private boolean isMyServiceRunning(Class<?> serviceClass) {
                ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                        if (serviceClass.getName().equals(service.service.getClassName())) {
                                return true;
                        }
                }
                return false;
        }
@Override
public void onBackPressed(){

        if(boomMenuButtonInActionBar.isClosed()
        &&boomInfo.isClosed()){
        super.onBackPressed();
        }else{
        boomMenuButtonInActionBar.dismiss();
        boomInfo.dismiss();
        }

        }
        @Override
        public void onStop() {
                super.onStop();
        }

        @Override
        public void onClick(View v){

        }

        @Override
        public void toShow() {

        }

        @Override
        public void showing(float fraction) {

        }

        @Override
        public void showed() {

        }

        @Override
        public void toHide() {

        }

        @Override
        public void hiding(float fraction) {

        }

        @Override
        public void hided() {

        }
}