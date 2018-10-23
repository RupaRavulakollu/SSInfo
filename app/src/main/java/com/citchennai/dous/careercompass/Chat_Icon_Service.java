package com.citchennai.dous.careercompass;


import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
//import static com.google.android.gms.internal.zzip.runOnUiThread;

public class Chat_Icon_Service extends Service
{
    @Override
    public void onDestroy(){
        mWindowManager.removeView(image);
        super.onDestroy();
    }
    private WindowManager mWindowManager;
    private ImageView image;

    public void onCreate() {
       super.onCreate();
        image = new ImageView(this);

        image.setImageResource(R.drawable.tweet_float_icon);


        mWindowManager = (WindowManager)getSystemService(WINDOW_SERVICE);

        final WindowManager.LayoutParams paramsF = new WindowManager.LayoutParams(
                120,
                120,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        paramsF.gravity = Gravity.CENTER | Gravity.RIGHT;
        paramsF.x=0;
        paramsF.y=0;
        mWindowManager.addView(image, paramsF);

        try{
            final int[] number_of_clicks = {0};
            final boolean[] thread_started = {false};
            final int DELAY_BETWEEN_CLICKS_IN_MILLISECONDS = 250;

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(Chat_Icon_Service.this, MainActivity.class);
//                    intent.putExtra("chat_room_id", "1");
//                    intent.putExtra("name", "TWEET");
//                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
                    ++number_of_clicks[0];
                    if(!thread_started[0]) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                thread_started[0] = true;
                                try {
                                    Thread.sleep(DELAY_BETWEEN_CLICKS_IN_MILLISECONDS);
                                    if (number_of_clicks[0] == 1) {
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Toast.makeText(Chat_Icon_Service.this, "Please Tap once again to chat", Toast.LENGTH_SHORT).show();
//                                            }
//                                        });

                                    } else if (number_of_clicks[0] == 2) {

                                        Intent intent = new Intent(Chat_Icon_Service.this, MainChatActivity.class);
//                                        intent.putExtra("chat_room_id", "1");
//                                        intent.putExtra("name", "TWEET");
//                                        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);


                                    }
                                    number_of_clicks[0] = 0;
                                    thread_started[0] = false;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }
            });

            image.setOnTouchListener(new View.OnTouchListener() {
                WindowManager.LayoutParams paramsT = paramsF;
                private int initialX;
                private int initialY;
                private float initialTouchX;
                private float initialTouchY;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            initialX = paramsF.x;
                            initialY = paramsF.y;
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            paramsF.x = initialX - (int) (event.getRawX() - initialTouchX);
                            paramsF.y = initialY + (int) (event.getRawY() - initialTouchY);
                            mWindowManager.updateViewLayout(v, paramsF);
                            break;
                    }
                    return false;
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}